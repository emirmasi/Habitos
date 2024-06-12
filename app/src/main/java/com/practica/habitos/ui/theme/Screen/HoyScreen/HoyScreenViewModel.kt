package com.practica.habitos.ui.theme.Screen.HoyScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.practica.habitos.Data.Models.DateItem
import com.practica.habitos.Data.Models.UserHabitLog
import java.time.LocalDate

class HoyScreenViewModel : ViewModel()
{
    val fechaActual = LocalDate.now()
    val habitos = listOf<UserHabitLog>(

    )
    var dateInRange = mutableListOf<DateItem>()

    private val _hoy = mutableStateOf<DateItem?>(null)
    val hoy: State<DateItem?> = _hoy

    init {
        dateInRange = loadDateInRange()
        actualizarHoy(convertLocalDateToDateItem(fechaActual))
    }

    fun actualizarHoy(hoyNew:DateItem){
        _hoy.value = hoyNew
    }
    fun loadDateInRange(): MutableList<DateItem> {
        val inicio = LocalDate.now().minusMonths(3)
        val fin = LocalDate.now().plusMonths(3)
        return getDayBetween(inicio,fin)
    }

    fun getDayBetween(inicio: LocalDate, fin: LocalDate): MutableList<DateItem> {

        val datesBetweenStarAndEnd = mutableListOf<DateItem>()
        var fechaActual = inicio
        while (!fechaActual.isAfter(fin)){

            datesBetweenStarAndEnd.add(convertLocalDateToDateItem(fechaActual))
            fechaActual = fechaActual.plusDays(1)
        }
        return datesBetweenStarAndEnd
    }
    fun convertLocalDateToDateItem(fecha: LocalDate):DateItem{
        return DateItem(fecha.dayOfMonth,fecha.month.value,fecha.year,fecha.dayOfWeek)
    }

    fun returnTodayDateInRange():DateItem{

        val datefind: DateItem = dateInRange.find { date->
            date.day == fechaActual.dayOfMonth &&
                    date.month == fechaActual.month.value &&
                    date.year == fechaActual.year &&
                    date.dayOfWeek == fechaActual.dayOfWeek
        }!!
        return datefind
    }
}