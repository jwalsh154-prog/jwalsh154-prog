package com.example.calender.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.calender.R

object HabitData {
    val allHabits = listOf(
        Habit(1, "Hydrate", 0xFF2196F3.toInt(), R.drawable.baseline_water_drop_24,"Stay hydrated by drinking water throughout the day, try to go for 4 bottles or more mark times where you want to drink. Here are some options for you \n\n\n\nTips and options:\n*Drink Before Meals\n*Drink water regularly\n*Track intake "),
        Habit(2, "Exercise", 0xFF4CAF50.toInt(), R.drawable.outline_bike_lane_24, "Do physical activity to help with future health, try to go for 30 minutes. \n\n\n\n Tips and options:\n*Set a specific time each day for a workout\n*Start small, don't overdo it\n*Have fun while doing it, do activities you enjoy" ),
        Habit(3, "Read", 0xFFF44336.toInt(), R.drawable.outline_book_2_24 , "Reading a book for 30 minutes can help one relax and to expand knowledge, improve here. \n\n\n\n Tips and options:\n*Set a specific time each day for reading\n*Choose books that interest you and ones you desire to read\n*Mix the genres, give yourself variety while still reading your favorites"),
        Habit(4,"Sleep" ,0xFFFFEB3B.toInt(), R.drawable.outline_bedtime_24,"Take time of your screen try to limited your time on your phone, or meditate and relax for 30 mins\n\n\n\n Tips and options:\n*Set that specific time each day to sleep\n*Limit screen time before bed\n*Avoid heavy meals and caffeine close to bedtime  ")

    )


}