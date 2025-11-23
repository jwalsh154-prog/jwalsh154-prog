package com.example.calender

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.calender.ui.theme.HabitData





class HabitDetails : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_details)

        val habitId = intent.getIntExtra("habit_id", -1)
        val  habit = HabitData.allHabits.find { it.id == habitId } ?: return

        val icon = findViewById<ImageView>(R.id.habitDetailsIcon)
        val name = findViewById<TextView>(R.id.habitDetailName)
        val colorDot = findViewById<ImageView>(R.id.habitDetailColor)
        val addButton = findViewById<Button>(R.id.buttonAddToCalendar)

        icon.setImageResource(habit.iconResId)
        name.text = habit.name
        colorDot.setColorFilter(habit.color)

        addButton.setOnClickListener {

         val intent = Intent(this, MainActivity::class.java)
         intent.putExtra("habit_id",habit.id)
         startActivity(intent)
        }

    }


}