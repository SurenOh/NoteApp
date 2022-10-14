package com.example.noteapp.screens.home

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.screens.base.BaseFragment
import com.example.noteapp.util.swipe.SwipeToDelete
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding != null) {
            return binding.root
        }
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener { viewModel.addNewNote() }
        setupAdapter()
        setupSwipeAction()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.setAdapterNotes(notes)
            binding.homeRecycler.isVisible = adapter.notes.isNotEmpty()
            binding.noInfo.isVisible = adapter.notes.isEmpty()
        }
    }

    private fun setupAdapter() {
        adapter = HomeAdapter(listOf())
        binding.homeRecycler.adapter = this@HomeFragment.adapter

        adapter.onClickNote = { note -> navigate(HomeFragmentDirections.goToDetail(note)) }
    }

    private fun setupSwipeAction() {
        val swipeToDeleteCallBack = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteNote(adapter.notes[position])
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.homeRecycler)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.notes.removeObservers(viewLifecycleOwner)
    }
}
