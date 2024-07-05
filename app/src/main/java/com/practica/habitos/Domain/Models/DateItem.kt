package com.practica.habitos.Domain.Models

import java.time.DayOfWeek
import java.time.LocalDate

class DateItem(
    val day: Int,
    val month: Int,
    val year: Int,
    val dayOfWeek: DayOfWeek,
) {
    fun convertToString(): String = "$day/$month/$year"

    fun esFechaMayor(fecha: DateItem?): Boolean {
        if (fecha != null) {
            return when {
                this.year > fecha.year -> true
                this.year < fecha.year -> false
                this.month > fecha.month -> true
                this.month < fecha.month -> false
                this.day > fecha.day -> true
                else -> false
            }
        } else {
            return false
        }
    }

    fun isDateActual():Boolean{
        return this.day == LocalDate.now().dayOfMonth &&
                this.month == LocalDate.now().monthValue &&
                this.year == LocalDate.now().year
    }
}
