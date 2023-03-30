package com.iggos.speedyworkoutapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomeFragment : Fragment() {

    lateinit var buttonEasy : Button
    lateinit var buttonMedium : Button
    lateinit var buttonChallenging : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        buttonEasy = view.findViewById(R.id.button_easy)
        buttonMedium = view.findViewById(R.id.button_medium)
        buttonChallenging = view.findViewById(R.id.button_challenging)

        val intent = Intent(activity, TrainingActivity::class.java)

        buttonEasy.setOnClickListener {
            intent.putExtra("difficulty", "easy")
            startActivity(intent)
        }
        buttonMedium.setOnClickListener {
            intent.putExtra("difficulty", "medium")
            startActivity(intent)
        }
        buttonChallenging.setOnClickListener {
            intent.putExtra("difficulty", "challenging")
            startActivity(intent)
        }

        return view
    }

}