package com.example.calender.ui.theme

import android.graphics.Color
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDisplayDecor(private val colors: List<Int>, private val dates: Collection<CalendarDay>) :

    DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        colors.forEachIndexed {i , color ->
        view?.addSpan(DotSpan(8f + (i * 2), color))}
    }
}