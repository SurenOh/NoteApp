package com.example.noteapp.screens.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.database.entity.Note
import com.example.noteapp.databinding.NoteItemBinding
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class HomeAdapter(var notes: List<Note>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private lateinit var binding: NoteItemBinding

    private val DEFAULT_FORMAT = SimpleDateFormat("dd.MM.yyyy")
    private val TODAY_FORMAT = SimpleDateFormat("hh:mm")

    var onClickNote: ((Note) -> Unit)? = null

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class ViewHolder(private var binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            with(binding) {
                title.text = note.title
                description.text = note.description
                changeDate.text = getFormat(note).format(Date(note.changeDate))
                cardView.setOnClickListener { onClickNote?.invoke(note) }
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapterNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }

    fun getFormat(note: Note): SimpleDateFormat {
        val startOfDay = Instant.now().truncatedTo(ChronoUnit.DAYS)
        val noteDay = Date(note.changeDate).toInstant()
        return if (noteDay.isAfter(startOfDay)) TODAY_FORMAT
        else {
            DEFAULT_FORMAT
        }
    }
}