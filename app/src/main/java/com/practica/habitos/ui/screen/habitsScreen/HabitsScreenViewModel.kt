package com.practica.habitos.ui.screen.habitsScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.practica.habitos.domain.models.DateItem
import com.practica.habitos.domain.models.convertToDIADELASEMANA
import java.time.LocalDate

class HabitsScreenViewModel: ViewModel() {
    private val _date = mutableStateOf(convertLocalDateToDateItem(LocalDate.now()))
    val date: State<DateItem> = _date

    private val _semana = mutableStateOf<List<DateItem>>(emptyList())
    val semana: State<List<DateItem>> = _semana


    private fun getSemana(){
        val inicio = LocalDate.now()
        val fin  = inicio.plusDays(7)

        repeat(7){it->
            _semana.value = _semana.value + convertLocalDateToDateItem(inicio.plusDays(it.toLong()))
        }
    }

    // fix: esta funcion deberia ir en la clase DateItem
    private fun convertLocalDateToDateItem(fecha: LocalDate): DateItem =
        DateItem(fecha.dayOfMonth, fecha.month.value, fecha.year,fecha.dayOfWeek.convertToDIADELASEMANA(fecha.dayOfWeek))

}