package com.example.calender.ui.theme

import com.example.calender.R

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*





class EventListAdapter (
    private val context : Context,
    private val events : MutableList<UserEvent>
) : BaseAdapter() {
    override fun getCount(): Int = events.size

    override fun getItem(position: Int): Any = events[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.event_checklist, parent, false)


        val event = events[position]

        val titleText = view.findViewById<TextView>(R.id.eventTitle)
        val checkbox = view.findViewById<CheckBox>(R.id.eventCheck)

        titleText.text = event.title
        checkbox.isChecked = event.isCompleted

        checkbox.setOnCheckedChangeListener { _: CompoundButton, isChecked : Boolean ->

            event.isCompleted = isChecked

        }

       return view

    }
}



