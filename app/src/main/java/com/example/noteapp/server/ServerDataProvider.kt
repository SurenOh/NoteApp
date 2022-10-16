package com.example.noteapp.server

import com.example.noteapp.server.dto.NoteDto
import com.example.noteapp.util.preferences.AppPreferences

object ServerDataProvider {
    private val list = mutableListOf<NoteDto>()

    fun getDataList(): MutableList<NoteDto> {
        if (AppPreferences.getBoolean()) {
            list.add(NoteDto("New Note1", "Lorem ipsum dolor sit amet", 1649286674000))
            list.add(NoteDto("New Note2", "Consectetur adipiscing elit", 1648941074000))
            list.add(NoteDto("New Note3", "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua", 1649718674000))
            list.add(NoteDto("New Note4", "Ut enim ad minim veniam", 1650755474000))
            list.add(NoteDto("New Note5;", "Quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 1654643474000))
            list.add(NoteDto("New Note6", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur", 1641165074000))
            list.add(NoteDto("New Note7", "Excepteur sint occaecat cupidatat non proident", 1641510674000))
            list.add(NoteDto("New Note8", "Sunt in culpa qui officia deserunt mollit anim id est laborum.", 1660345874000))
        }
        return list
    }
}