package com.example.calender.ui.theme

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calender.ui.theme.HelpActivity
import com.example.calender.ui.theme.NotificationHelper
import com.example.calender.ui.theme.PaymentActivity
import com.example.calender.R

class DashboardActivity : AppCompatActivity() {
    private lateinit var notificationHelper: NotificationHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        val main = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        notificationHelper = NotificationHelper(this)
        notificationHelper.createNotificationChannel()
        val paymentButton = findViewById<Button>(R.id.btnGoToPayment)
        val reminderButton = findViewById<Button>(R.id.btnSendReminder)
        val helpButton = findViewById<Button>(R.id.btnGoToHelp)

        paymentButton.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
        reminderButton.setOnClickListener {
            notificationHelper.showReminder("Remember to complete your habit today.")
        }
        helpButton.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
    }
}