package com.example.calender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.MenuItem
import androidx.compose.runtime.State
import android.widget.LinearLayout
import android.view.View
import androidx.appcompat.widget.Toolbar
import android.graphics.Color
import android.widget.Button
import com.example.calender.ui.theme.HelpActivity
import com.example.calender.ui.theme.PaymentActivity


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
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.navigationIcon?.setTint(Color.BLACK)

        val help = findViewById<Button>(R.id.help)
        help.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
        val payment = findViewById<Button>(R.id.payment)
        payment.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

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