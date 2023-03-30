package com.iggos.speedyworkoutapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class StatisticsFragment : Fragment() {

    lateinit var numOfTraining : TextView
    lateinit var minutes : TextView
    lateinit var statisticsView : View
    lateinit var congratulations : TextView
    lateinit var emoji : ImageView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        statisticsView = inflater.inflate(R.layout.fragment_statistics, container, false)

        numOfTraining = statisticsView.findViewById(R.id.num_of_trainings)
        minutes = statisticsView.findViewById(R.id.minutes)
        congratulations = statisticsView.findViewById(R.id.congratulationsTextView)
        emoji = statisticsView.findViewById(R.id.imageView)

        sharedPreferences = requireContext().getSharedPreferences("progress", Context.MODE_PRIVATE)

        val totalTimesTrained = sharedPreferences.getInt("times_trained_key", 0)
        val totalMinutesTrained = sharedPreferences.getInt("minutes_key", 0)

        numOfTraining.text = totalTimesTrained.toString()
        val minutesTrainedText = "$totalMinutesTrained min"
        minutes.text = minutesTrainedText

        if (totalTimesTrained == 0) {
            congratulations.visibility = View.INVISIBLE
            emoji.visibility = View.INVISIBLE
        }

        return statisticsView
    }

}