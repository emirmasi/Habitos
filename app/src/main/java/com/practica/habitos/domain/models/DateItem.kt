package com.practica.habitos.domain.models

import java.time.DayOfWeek
import java.time.LocalDate

///el dayOfWeek me devuelve un int , realcionado con la semana
class DateItem(
    val day: Int,
    val month: Int,
    val year: Int,
    val dayOfWeek: DIASDELASEMANA,
) {
    fun convertToString(): String = "$day/$month/$year"

    fun formatDate(): String = "$day de ${convertMonthToString(month)} de $year"
    fun convertMonthToString(month:Int):String{
        val mes = mapOf(
            1 to "Enero",
            2 to "Febrero",
            3 to "Marzo",
            4 to "Abril",
            5 to "Mayo",
            6 to "Junio",
            7 to "Julio",
            8 to "Agosto",
            9 to "Septiembre",
            10 to "Octubre",
            11 to "Noviembre",
            12 to "Diciembre"
        )
        return mes.getValue(month)
    }


    fun convertDayOfWeekToDiaDeLaSemana(dayOfWeek: DayOfWeek):DIASDELASEMANA{
        val dia = mapOf(
            1 to DIASDELASEMANA.LUNES,
            2 to DIASDELASEMANA.MARTES,
            3 to DIASDELASEMANA.MIERCOLES,
            4 to DIASDELASEMANA.JUEVES,
            5 to DIASDELASEMANA.VIERNES
        )
        return dia.getValue(dayOfWeek.value)
    }
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
