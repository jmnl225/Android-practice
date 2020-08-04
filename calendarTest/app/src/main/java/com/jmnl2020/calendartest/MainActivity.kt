package com.jmnl2020.calendartest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

fun refreshCurrentMonth(calendar: Calendar) {
    val sdf = SimpleDateFormat("yyyy MM", Locale.KOREAN)
    tv_current_month.text = sdf.format(calendar.time)
}
