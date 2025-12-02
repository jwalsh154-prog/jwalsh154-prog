package com.example.calender

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.calender.ui.theme.HabitData





class HabitDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_details)

        val habitId = intent.getIntExtra("habit_id", -1)
        val habit = HabitData.allHabits.find { it.id == habitId } ?: return

        val icon = findViewById<ImageView>(R.id.habitDetailsIcon)
        val name = findViewById<TextView>(R.id.habitDetailName)
        val des = findViewById<TextView>(R.id.habitDetailDes)
        val colorDot = findViewById<ImageView>(R.id.habitDetailColor)
        val addButton = findViewById<Button>(R.id.buttonAddToCalendar)

        icon.setImageResource(habit.iconResId)
        name.text = habit.name
        des.text = habit.des
        colorDot.setColorFilter(habit.color)

        addButton.setOnClickListener {

            val intent = Intent(this, CalendarActivity::class.java)
            intent.putExtra("habit_id", habit.id)
            startActivity(intent)
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.navigationIcon?.setTint(Color.BLACK)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
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
}
