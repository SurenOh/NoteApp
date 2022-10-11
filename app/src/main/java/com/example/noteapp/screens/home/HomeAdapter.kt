package com.example.noteapp.screens.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.database.entity.Note
import com.example.noteapp.databinding.NoteItemBinding
import java.util.*

class HomeAdapter(var notes: List<Note>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private lateinit var binding: NoteItemBinding

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class ViewHolder(private var binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            with(binding) {
                title.text = note.title
                description.text = note.description
                changeDate.text = Date(note.changeDate).toString()
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapterNotes (newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}