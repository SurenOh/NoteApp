package com.example.noteapp.screens.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.noteapp.MainActivity

open class BaseFragment : Fragment() {

    fun navigate(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    fun popBackStack() {
        findNavController().popBackStack()
    }

    fun hideKeyboard() {
        (requireActivity() as? MainActivity)?.hideKeyboard()
    }
}
