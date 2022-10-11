package com.example.noteapp.screens.detail

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.noteapp.R
import com.example.noteapp.database.entity.Note
import com.example.noteapp.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val note = arguments?.getSerializable("note") as Note
        with(binding) {
            title.setText(note.title)
            description.setText(note.description)
        }
        val menuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.findItem(R.id.saveButton).isVisible = true
                menu.findItem(R.id.saveButton).isEnabled = true
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.saveButton) {
                    viewModel.updateNote(note, binding.title.text.toString(), binding.description.text.toString())
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        return binding.root
    }

}