package com.example.neowsapp.infrastructure.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "yyyy-MM-dd"): String = SimpleDateFormat(pattern, Locale.getDefault()).format(this)
fun Date.addDays(days: Int): Date = add(Calendar.DAY_OF_MONTH, days)

private fun Date.add(field: Int, amount: Int): Date {
    Calendar.getInstance().apply {
        time = this@add
        add(field, amount)
        return time
    }
}