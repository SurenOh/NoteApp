package com.example.noteapp.screens.home

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.screens.base.BaseFragment
import com.example.noteapp.util.connection.NetworkHelper
import com.example.noteapp.util.preferences.AppPreferences
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (_binding != null) return binding.root
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        AppPreferences.init(requireContext())
        NetworkHelper.init(requireContext())
        binding.fab.setOnClickListener { viewModel.addNewNote(resources.getString(R.string.new_note)) }
        binding.btnTryAgain.setOnClickListener { getNotesFromServer() }

        setupSwipeAction()
        getNotesFromServer()
        setupAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun getNotesFromServer() = with(binding) {
        viewModel.getNotesFromServer(AppPreferences.getBoolean())
        noInfo.isVisible = false
        if (NetworkHelper.checkNetwork()) {
            fab.isClickable = true
            fab.isVisible = true
            btnTryAgain.visibility = GONE
            btnTryAgain.isClickable = false
            if (!AppPreferences.getBoolean()) viewModel.setupProgressBar()
        } else {
            if (AppPreferences.getBoolean()) {
                btnTryAgain.visibility = Button.VISIBLE
                btnTryAgain.isClickable = true
                fab.isClickable = false
                fab.isVisible = false
                noInfo.text = resources.getString(R.string.no_connection)
                noInfo.isVisible = true
            } else {
                showNoConnection()
            }
        }
    }

    private fun setupObservers() {
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.setAdapterNotes(notes.toMutableList())
            binding.homeRecycler.isVisible = adapter.notes.isNotEmpty()
            if (!AppPreferences.getBoolean()) binding.noInfo.isVisible = adapter.notes.isEmpty()
        }
        viewModel.progress.observe(viewLifecycleOwner) { isLoading ->
            setProgress(isLoading)
        }
        viewModel.progressBar.observe(viewLifecycleOwner) { progress ->
            if (!NetworkHelper.checkNetwork()) showNoConnection()
            setProgressBar(progress)
        }
    }

    private fun setupAdapter() {
        adapter = HomeAdapter(mutableListOf())
        binding.homeRecycler.adapter = this@HomeFragment.adapter
        adapter.onClickNote = { note ->
            navigate(HomeFragmentDirections.goToDetail(note))
            setProgressBar(100)
        }
    }

    private fun setupSwipeAction() {
        val swipeToDeleteCallBack = object : SwipeItemCallBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deleteNote = adapter.notes[position]
                adapter.deleteNote(deleteNote, position)
                viewModel.deleteNote(deleteNote)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.homeRecycler)
    }

    private fun showNoConnection() {
        Snackbar.make(binding.coordinator, resources.getString(R.string.no_connection), Snackbar.LENGTH_LONG)
            .apply {
                setAction("") { getNotesFromServer() }
                setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.teal_200))
                val textView = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                textView.setOnClickListener {
                    getNotesFromServer()
                    dismiss()
                }
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_refresh, 0)
                show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.notes.removeObservers(viewLifecycleOwner)
        viewModel.progressBar.removeObservers(viewLifecycleOwner)
        viewModel.progress.removeObservers(viewLifecycleOwner)
    }
}
