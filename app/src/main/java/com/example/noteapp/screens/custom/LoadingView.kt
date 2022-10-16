package com.example.noteapp.screens.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.noteapp.R
import com.example.noteapp.databinding.ViewLoadingBinding

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewLoadingBinding =
        ViewLoadingBinding.inflate(LayoutInflater.from(context), this, true)
    private val spinAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.load_spinner_anim)

    fun showView() {
        binding.loadingViewSpinner.startAnimation(spinAnimation)
        binding.loadingViewContainer.visibility = VISIBLE
    }

    fun hideView() {
        binding.loadingViewSpinner.clearAnimation()
        binding.loadingViewContainer.visibility = GONE
    }
}
