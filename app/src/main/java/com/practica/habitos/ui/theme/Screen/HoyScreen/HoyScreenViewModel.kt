package com.practica.habitos.ui.theme.Screen.HoyScreen

import androidx.lifecycle.ViewModel
import com.practica.habitos.Data.Models.DateItem
import java.time.LocalDate

class HoyScreenViewModel : ViewModel()
{
    val dateInRange = loadDateInRange()

    fun loadDateInRange(): List<DateItem>{
        val inicio = LocalDate.now().minusMonths(3)
        val fin = inicio.plusMonths(3)
        return getDayBetween(inicio,fin)
    }
    fun getDayBetween(inicio: LocalDate, fin: LocalDate): List<DateItem> {

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