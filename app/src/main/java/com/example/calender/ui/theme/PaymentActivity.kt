package com.example.calender.ui.theme

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calender.HabitActivity
import com.example.calender.HabitDetails
import com.example.calender.R

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        val main = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnConfirm = findViewById<Button>(R.id.btnConfirmPayment)
        btnConfirm.setOnClickListener {
            Toast.makeText(this, "Payment confirmed for prototype", Toast.LENGTH_SHORT).show()
            finish()
        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.navigationIcon?.setTint(Color.BLACK)
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
