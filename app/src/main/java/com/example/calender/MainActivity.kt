package com.example.calender


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.prolificinteractive.materialcalendarview.CalendarDay
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.core.content.edit
import com.example.calender.ui.theme.EventDisplayDecor
import com.example.calender.ui.theme.HabitData
import com.example.calender.ui.theme.UserEvent
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity :  AppCompatActivity() {
    private val userEvents = mutableListOf<UserEvent>()

private lateinit var calendarView: MaterialCalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)




        val calendarView = findViewById<MaterialCalendarView>(R.id.calendarView)

        loadEvents()

        calendarView.selectedDate = CalendarDay.today()


        val incomingHabitId = intent.getIntExtra("habit_it", -1)
        if (incomingHabitId != -1){
            val habit = HabitData.allHabits.find { it.id == incomingHabitId}
            if(habit != null){
                showAddDate(CalendarDay.today())
            }
        }


        calendarView.setOnDateChangedListener { _, date, _ ->
            val eventsForDates = userEvents.filter { it.date == date }

            if (eventsForDates.isNotEmpty()) {
                val eventTitle = eventsForDates.map { it.title } .toTypedArray()
                android.app.AlertDialog.Builder(this)
                    .setTitle("Events on ${date.month }/${date.day}/${date.year}")
                    .setItems(eventTitle){_, index ->
                        val eventToDelete = eventsForDates[index]
                        userEvents.remove(eventToDelete)
                        saveEvent()
                        refreshDec()
                        Toast.makeText(this, "Deleted: ${eventToDelete.title}", Toast.LENGTH_SHORT).show()


                    }
                    .setPositiveButton("Add More") { dialog, _ -> dialog.dismiss()
                        showAddDate(date)
                    }
                    .setNegativeButton("Ok"){dialog, _ -> dialog.dismiss() }
                    .show()
            } else {
                showAddDate(date)
            }


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_habits -> {
                startActivity(Intent(this, HabitActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAddDate(date: CalendarDay) {

        val habitName = HabitData.allHabits.map { it.name }.toTypedArray()

        android.app.AlertDialog.Builder(this)
            .setTitle("Select an Event")
            .setItems(habitName) { _, habitIndex ->
                val selectedHabit = HabitData.allHabits[habitIndex]

                val editText = android.widget.EditText(this)
                editText.hint = "Event title"

                android.app.AlertDialog.Builder(this)
                    .setTitle("Add an Event for ${date.month}/${date.day}/${date.year}")
                    .setView(editText)
                    .setPositiveButton("Save") { dialog, _ ->

                        val finalTitle = editText.text.toString().ifBlank {  selectedHabit.name}

                        val newEvent =
                            UserEvent(date = date, title = finalTitle, color = selectedHabit.color)

                        userEvents.add(newEvent)
                        saveEvent()
                        refreshDec()
                        dialog.dismiss()

                    }
                    .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                    .show()

            }
            .setNegativeButton("Skip Habit") { _, _ ->


                val editText = android.widget.EditText(this)
                editText.hint = "Event title"

                android.app.AlertDialog.Builder(this)
                    .setTitle("Add a custom Event")
                    .setMessage("Add an Event for ${date.month}/${date.day}/${date.year}")
                    .setView(editText)
                    .setPositiveButton("Save") { dialog, _ ->
                        val eventTitle = editText.text.toString()
                        if (eventTitle.isNotBlank()) {

                            val newEvent = UserEvent(
                                date = date,
                                title = eventTitle,
                                color = android.graphics.Color.BLUE
                            )
                            userEvents.add(newEvent)
                            saveEvent()

                            val calendarView = findViewById<MaterialCalendarView>(R.id.calendarView)
                            calendarView.removeDecorators()

                            val dateColorMap = mutableMapOf<CalendarDay, MutableList<Int>>()

                            userEvents.forEach { event ->
                                val colors = dateColorMap.getOrPut(event.date) { mutableListOf() }
                                colors.add(android.graphics.Color.BLUE)

                            }
                            Toast.makeText(
                                this,
                                "Event: ${date.day}/${date.month + 1}/${date.year}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
            .show()
    }

    private fun loadEvents() {
        val shared = getSharedPreferences("calendar_prefs", MODE_PRIVATE)
        val json = shared.getString("events", null)

        if (json != null) {
            val gson = Gson()

            val type = object : TypeToken<MutableList<UserEvent>>() {}.type
            val loadedEvents: MutableList<UserEvent> = gson.fromJson(json, type)
            userEvents.clear()
            userEvents.addAll(loadedEvents)

        }
    }
        private fun saveEvent(){
            val shared = getSharedPreferences("calendar_prefs", MODE_PRIVATE)

            val gson = Gson()
            val json = gson.toJson(userEvents)

            shared.edit{
                putString("events", json)
            }
        }


    private fun refreshDec(){
        val calendarView = findViewById<MaterialCalendarView>(R.id.calendarView)
        calendarView.removeDecorators()

        val dateColorMap = mutableMapOf<CalendarDay, MutableList<Int>>()

        userEvents.forEach { event ->
            val colors = dateColorMap.getOrPut(event.date) { mutableListOf() }
            colors.add(android.graphics.Color.BLUE)

        }
        dateColorMap.forEach { (eventDates, colors)  ->

            calendarView.addDecorator(EventDisplayDecor(colors, listOf(eventDates)))

        }


    }





    }




