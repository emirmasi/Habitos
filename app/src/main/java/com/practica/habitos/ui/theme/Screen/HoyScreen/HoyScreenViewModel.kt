package com.practica.habitos.ui.theme.Screen.HoyScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.practica.habitos.Data.Models.DateItem
import com.practica.habitos.Data.Models.Habito
import java.time.LocalDate

class HoyScreenViewModel : ViewModel()
{
    val fechaActual = LocalDate.now()
    val habitos = listOf<Habito>()
    var dateInRange = mutableListOf<DateItem>()

    private val _hoy = mutableStateOf<DateItem?>(null)
    val hoy: State<DateItem?> = _hoy

    init {
        dateInRange = loadDateInRange()
        actualizarHoy(dateInRange.first())
    }

    fun actualizarHoy(hoyNew:DateItem){
        _hoy.value = hoyNew
    }
    fun loadDateInRange(): MutableList<DateItem> {
        val inicio = LocalDate.now().minusMonths(3)
        val fin = inicio.plusMonths(3)
        return getDayBetween(inicio,fin)
    }

    fun getDayBetween(inicio: LocalDate, fin: LocalDate): MutableList<DateItem> {

        val datesBetweenStarAndEnd = mutableListOf<DateItem>()
        var fechaActual = inicio
        while (!fechaActual.isAfter(fin)){

            DateItem(fechaActual.dayOfMonth,fechaActual.month.value,fechaActual.year,fechaActual.dayOfWeek)
            datesBetweenStarAndEnd.add(DateItem(fechaActual.dayOfMonth,fechaActual.month.value,fechaActual.year,fechaActual.dayOfWeek))

            fechaActual = fechaActual.plusDays(1)
        }

        return datesBetweenStarAndEnd
    }
}