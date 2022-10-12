package com.example.noteapp.server

import com.example.noteapp.server.dto.NoteDTO

object Server {
    private val list = mutableListOf<NoteDTO>()

    fun getManagerList() = list

}