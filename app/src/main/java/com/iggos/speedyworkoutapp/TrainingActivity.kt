package com.iggos.speedyworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

const val MILLISECONDS_IN_SECONDS = 1000
const val ONE_MINUTE = 60
const val TWO_MINUTES = 120
const val THREE_MINUTES = 180

class TrainingActivity : AppCompatActivity() {

    lateinit var timer : CountDownTimer

    private var totalTime : Long = 0
    private var totalTimeLeftInMillis : Long = totalTime

    lateinit var totalTimeTextView : TextView
    lateinit var timeLeftForExerciseTextView : TextView
    lateinit var exerciseImage: ImageView
    lateinit var exerciseDesc: TextView
    lateinit var trainingImagesWithDescription : TrainingImagesWithDescription

    var difficulty : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        totalTimeTextView = findViewById(R.id.totalTimeLeft)
        timeLeftForExerciseTextView = findViewById(R.id.timeLeftForExercise)
        exerciseImage = findViewById(R.id.exerciseImageView)
        exerciseDesc = findViewById(R.id.exerciseTextView)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        trainingImagesWithDescription = TrainingImagesWithDescription(this@TrainingActivity)
        setRandomExerciseImage()

        difficulty = intent.getStringExtra("difficulty")
        if (difficulty == "easy") {
            totalTime = (ONE_MINUTE * MILLISECONDS_IN_SECONDS).toLong()
        } else if (difficulty == "medium") {
            totalTime = (TWO_MINUTES * MILLISECONDS_IN_SECONDS).toLong()
        } else {
            totalTime = (THREE_MINUTES * MILLISECONDS_IN_SECONDS).toLong()
        }
        totalTimeLeftInMillis = totalTime
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }

    override fun onResume() {
        startTimer()
        super.onResume()
    }

    override fun onBackPressed() {
        onPause()
        createBackAlertDialog()
    }

    fun startTimer() {
        timer = object : CountDownTimer(totalTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinish: Long) {
                totalTimeLeftInMillis = millisUntilFinish
                updateText()
            }
            override fun onFinish() {
                updateText()
                timer.cancel()
                val intent = Intent(this@TrainingActivity, TrainingFinishedActivity::class.java)
                intent.putExtra("difficulty", difficulty)
                startActivity(intent)
                finish()
            }
        }.start()
    }

    fun updateText() {
        val remainingTotalTime : Int = (totalTimeLeftInMillis / MILLISECONDS_IN_SECONDS).toInt()
        totalTimeTextView.text = remainingTotalTime.toString()
        if (remainingTotalTime <= 3) {
            totalTimeTextView.setTextColor(resources.getColor(R.color.red))
        } else if (remainingTotalTime == 20) {
            totalTimeTextView.setTextColor(resources.getColor(R.color.text_color))
        }

        var exerciseTime = remainingTotalTime % 20
        if (exerciseTime == 0 && remainingTotalTime != 0) { exerciseTime = 20 }
        timeLeftForExerciseTextView.text = exerciseTime.toString()
        if (exerciseTime <= 3) {
            timeLeftForExerciseTextView.setTextColor(resources.getColor(R.color.red))

        } else if (exerciseTime == 20) {
            timeLeftForExerciseTextView.setTextColor(resources.getColor(R.color.text_color))
        }

        if (remainingTotalTime % 20 == 0 && remainingTotalTime != 0) {
            setRandomExerciseImage()
        }
    }

    private fun createBackAlertDialog() {
        val alertDialog = AlertDialog.Builder(this@TrainingActivity)
        alertDialog.setTitle(R.string.alert_dialog_title)
            .setMessage(R.string.alert_dialog_message)
            .setIcon(R.drawable.ic_baseline_question_mark_24)
            .setCancelable(false)
            .setNegativeButton(R.string.alert_dialog_answer_negative) { dialog, _ ->
                onResume()
                dialog.cancel()
            }
            .setPositiveButton(R.string.alert_dialog_answer_positive) { _, _ ->
                finish()
            }

        alertDialog.create().show()
    }

    private fun setRandomExerciseImage() {
        val randomExercise = trainingImagesWithDescription.images.random()
        exerciseImage.setImageResource(randomExercise.image)
        exerciseDesc.text = randomExercise.desc
    }
}