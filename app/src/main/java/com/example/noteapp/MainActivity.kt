package com.example.noteapp

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.noteapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun showNoConnection(view: View) {
        val snackBar = Snackbar.make(view, "Нет интернета", Snackbar.LENGTH_LONG)
        snackBar.setBackgroundTint(ContextCompat.getColor(this, R.color.teal_200))
        snackBar.show()
    }

    fun hideKeyboard() {
        val imm: InputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = this.currentFocus
        if (view == null) view = View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
