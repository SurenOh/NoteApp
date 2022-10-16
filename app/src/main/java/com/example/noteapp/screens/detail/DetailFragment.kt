package com.example.noteapp.screens.detail

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentDetailBinding
import com.example.noteapp.models.NoteModel
import com.example.noteapp.screens.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var note: NoteModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (_binding != null) return binding.root
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.imageButton.setOnClickListener {
            popBackStack()
            hideKeyboard()
        }
        setupViews()
        setupMenu()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        note = args.note
        etTitle.setText(note.title)
        etDescription.setText(note.description)
    }

    private fun setupMenu() = with(binding) {
        val menuHost = requireActivity()
        menuHost.addMenuProvider(
            object : MenuProvider {

                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menu.findItem(R.id.saveButton).isVisible = true
                    menu.findItem(R.id.saveButton).isEnabled = true
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    if (menuItem.itemId == R.id.saveButton) {
                        viewModel.updateNote(
                            note,
                            binding.etTitle.text.toString(),
                            binding.etDescription.text.toString()
                        )
                        hideKeyboard()
                        binding.etDescription.clearFocus()
                        binding.etTitle.clearFocus()
                    }
                    return true
                }
            },
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
