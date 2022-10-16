package com.example.noteapp.util.preferences

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private const val PREF_NAME = "MyPref"
    private const val MODE = Context.MODE_PRIVATE
    private const val booleanName = "isFirstAction"

    fun init(context: Context) {
        pref = context.getSharedPreferences(PREF_NAME, MODE)
        editor = pref.edit()
    }

    fun getBoolean() = pref.getBoolean(booleanName, true)

    fun setBoolean(boolean: Boolean) {
        editor.putBoolean(booleanName, boolean)
        editor.apply()
    }
}
