package com.iggos.speedyworkoutapp

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.iggos.speedyworkoutapp.databinding.ActivityTrainingFinishedBinding


class TrainingFinishedActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTrainingFinishedBinding
    private lateinit var sharedPreferences: SharedPreferences

    var difficulty : String? = null
    var trainedMinutes : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingFinishedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val animationBackground = binding.finishedLayout.background as AnimationDrawable
        animationBackground.setEnterFadeDuration(10)
        animationBackground.setExitFadeDuration(5000)
        animationBackground.start()

        difficulty = intent.getStringExtra("difficulty")
        trainedMinutes = when (difficulty) {
            "easy" -> 1
            "medium" -> 2
            else -> 3
        }

        saveProgress()

        binding.backToMenuBtn.setOnClickListener {
            finish()
        }
    }

    fun saveProgress() {
        sharedPreferences = this.getSharedPreferences("progress", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("minutes_key", sharedPreferences.getInt("minutes_key", 0) + trainedMinutes!!)
        editor.putInt("times_trained_key", sharedPreferences.getInt("times_trained_key", 0) + 1)
        editor.apply()
    }
}