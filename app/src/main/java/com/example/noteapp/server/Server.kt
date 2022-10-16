package com.example.noteapp.server

object Server {
    fun getNotesList() = ServerDataProvider.getDataList()
}
