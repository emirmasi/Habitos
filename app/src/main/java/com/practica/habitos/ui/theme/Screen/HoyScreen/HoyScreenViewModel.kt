package com.practica.habitos.ui.theme.Screen.HoyScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.practica.habitos.Data.Models.DateItem
import com.practica.habitos.Data.Models.UserHabitLog
import java.time.LocalDate

class HoyScreenViewModel : ViewModel()
{
    private val _hoy = mutableStateOf<DateItem?>(null)
    val hoy: State<DateItem?> = _hoy

    private var _dateInRange = mutableStateOf<List<DateItem>>(emptyList())
    val dateInRange: State<List<DateItem>> = _dateInRange

    val fechaActual = LocalDate.now()

    private val _listaDehabitos = mutableStateOf<List<UserHabitLog>>(emptyList())
    val habitos:State<List<UserHabitLog>> = _listaDehabitos

    init {
        loadDateInRange()
        actualizarHoy(convertLocalDateToDateItem(fechaActual))
    }

    fun actualizarHoy(hoyNew:DateItem){
        _hoy.value = hoyNew
    }
    fun loadDateInRange() {
        val inicio = LocalDate.now().minusMonths(3)
        val fin = LocalDate.now().plusMonths(3)
        _dateInRange.value =  getDayBetween(inicio,fin)
    }

    fun getDayBetween(inicio: LocalDate, fin: LocalDate): List<DateItem> {
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
        val datefind: DateItem = dateInRange.value.find { date->
            date.day == fechaActual.dayOfMonth &&
                    date.month == fechaActual.month.value &&
                    date.year == fechaActual.year &&
                    date.dayOfWeek == fechaActual.dayOfWeek
        }!!
        return datefind
    }
}