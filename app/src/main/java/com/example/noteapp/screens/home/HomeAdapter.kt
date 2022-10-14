package com.example.noteapp.screens.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteItemBinding
import com.example.noteapp.models.NoteModel
import com.example.noteapp.util.DateUtil
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

typealias OnNoteClickCallBack = (NoteModel) -> Unit

class HomeAdapter(var notes: List<NoteModel>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private lateinit var binding: NoteItemBinding

    var onClickNote: OnNoteClickCallBack? = null

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAdapterNotes(newNotes: List<NoteModel>) {
        notes = newNotes
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(noteModel: NoteModel) = with(binding) {
            title.text = noteModel.title
            description.text = noteModel.description
            changeDate.text = getFormat(noteModel).format(Date(noteModel.changeDate))
            itemView.setOnClickListener { onClickNote?.invoke(noteModel) }
        }
        private fun getFormat(noteModel: NoteModel): SimpleDateFormat {
            val startOfDay = Instant.now().truncatedTo(ChronoUnit.DAYS)
            val noteDay = Date(noteModel.changeDate).toInstant()

            return if (noteDay.isAfter(startOfDay)) DateUtil.todayDateFormat
            else DateUtil.defaultDateFormat
        }
    }
}
