package com.example.calender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.MenuItem
import androidx.compose.runtime.State
import android.widget.LinearLayout
import android.view.View
import androidx.appcompat.widget.Toolbar


class HabitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)

        findViewById<LinearLayout>(R.id.habitWater).setOnClickListener {
            openHabitDetails(1)
        }
        findViewById<LinearLayout>(R.id.habitExe).setOnClickListener {
            openHabitDetails(2)
        }
        findViewById<LinearLayout>(R.id.habitSleep).setOnClickListener {
            openHabitDetails(3)
        }
        findViewById<LinearLayout>(R.id.habitRead).setOnClickListener {
            openHabitDetails(4)
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home->{
                finish()
                true
            }

            R.id.menu_habits -> {
                startActivity(Intent(this, HabitActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun openHabitDetails(habitId: Int){
        val intent = Intent(this, HabitDetails::class.java)
        intent.putExtra("habit_id", habitId)
        startActivity(intent)
    }

}