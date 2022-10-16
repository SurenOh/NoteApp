package com.example.noteapp

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.util.connection.NetworkHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun hideKeyboard() {
        val imm: InputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = this.currentFocus
        if (view == null) view = View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun setProgress(isLoading: Boolean) {
        if (isLoading) binding.viewLoad.showView()
        else binding.viewLoad.hideView()
    }

    fun setProgressBar(progress: Int) {
        if (progress < 100 && NetworkHelper.checkNetwork()) {
            binding.progressBar.visibility = VISIBLE
            binding.progressBar.progress = progress
        } else {
            binding.progressBar.visibility = GONE
        }
    }
}
