package com.example.noteapp.screens.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener { viewModel.addNewNote() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
    }

    private fun setupObservers () {
        viewModel.getAllNotes().observe(viewLifecycleOwner) {notes ->
            adapter.setAdapterNotes(notes)
            binding.homeRecycler.isVisible = adapter.notes.isNotEmpty()
        }
    }

    private fun setupAdapter () {
        adapter = HomeAdapter(listOf())
        binding.homeRecycler.apply {
            adapter = this@HomeFragment.adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        adapter.onClickNote = {note ->
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }

}