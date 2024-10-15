package com.practica.habitos.ui.screen.habitsScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.practica.habitos.domain.models.DateItem
import java.time.LocalDate

class HabitsScreenViewModel: ViewModel() {
    private val _date = mutableStateOf(convertLocalDateToDateItem(LocalDate.now()))
    val date: State<DateItem> = _date

    //fix: esta funcion deberia ir en la clase DateItem
    private fun convertLocalDateToDateItem(fecha: LocalDate): DateItem =
        DateItem(fecha.dayOfMonth, fecha.month.value, fecha.year, fecha.dayOfWeek)

}