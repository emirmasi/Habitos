package com.practica.habitos.Domain.Models

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

    fun esFechaMayor(fecha: DateItem?):Boolean{
        if (fecha != null) {
            return when {
                this.year > fecha.year  -> true
                this.year < fecha.year -> false
                this.month > fecha.month -> true
                this.month < fecha.month -> false
                this.day > fecha.day -> true
                else -> false
            }
        }else{
            return false
        }
    }
}