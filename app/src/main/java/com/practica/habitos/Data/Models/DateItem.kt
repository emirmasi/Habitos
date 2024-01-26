package com.practica.habitos.Data.Models

import java.time.DayOfWeek
class DateItem(
    val day: Int,
    val month: Int,
    val year: Int,
    val dayOfWeek: DayOfWeek
){
    fun toConvert(): String {
        return "$day/$month/$year"
    }

}