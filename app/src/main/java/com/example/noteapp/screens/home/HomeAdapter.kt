package com.example.noteapp.screens.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteItemBinding
import com.example.noteapp.model.NoteModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class HomeAdapter(var notes: List<NoteModel>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private lateinit var binding: NoteItemBinding

    private val DEFAULT_FORMAT = SimpleDateFormat("dd.MM.yyyy")
    private val TODAY_FORMAT = SimpleDateFormat("hh:mm")

    var onClickNote: ((NoteModel) -> Unit)? = null

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    fun getFormat(noteModel: NoteModel): SimpleDateFormat {
        val startOfDay = Instant.now().truncatedTo(ChronoUnit.DAYS)
        val noteDay = Date(noteModel.changeDate).toInstant()
        return if (noteDay.isAfter(startOfDay)) TODAY_FORMAT
        else {
            DEFAULT_FORMAT
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapterNotes (newNotes: List<NoteModel>) {
        notes = newNotes
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(noteModel: NoteModel) {
            with(binding) {
                title.text = noteModel.title
                description.text = noteModel.description
                changeDate.text = getFormat(noteModel).format(Date(noteModel.changeDate))
                cardView.setOnClickListener { onClickNote?.invoke(noteModel) }
            }
        }

    }
}