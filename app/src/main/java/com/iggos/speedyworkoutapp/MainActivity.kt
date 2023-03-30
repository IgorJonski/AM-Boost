package com.iggos.speedyworkoutapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.iggos.speedyworkoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(HomeFragment())

//        clearSharedPreferences()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> changeFragment(HomeFragment())
                R.id.statistics -> changeFragment(StatisticsFragment())
                R.id.settings -> changeFragment(SettingsFragment())
                else -> {}
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    private fun clearSharedPreferences() {
        // for debug and testing only
        val sharedPreferences = this.getSharedPreferences("progress", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}