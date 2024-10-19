package com.practica.habitos.ui.screen.hoyScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.practica.habitos.domain.models.Categoria
import com.practica.habitos.domain.models.DateItem
import com.practica.habitos.domain.models.UserHabitLog
import com.practica.habitos.domain.models.convertToDIADELASEMANA
import java.time.LocalDate

class HoyScreenViewModel : ViewModel() {
    private val _date = mutableStateOf(convertLocalDateToDateItem(LocalDate.now()))
    val date: State<DateItem> = _date

    private var _dateInRange = mutableStateOf<List<DateItem>>(emptyList())
    val dateInRange: State<List<DateItem>> = _dateInRange

    val today = LocalDate.now()

    private val _listaDehabitos = mutableStateOf<List<UserHabitLog>>(emptyList())
    val habitos: State<List<UserHabitLog>> = _listaDehabitos

    private val _listOfCategories = mutableStateOf<List<Categoria>>(emptyList())
    val listOfCategories: State<List<Categoria>> = _listOfCategories

    init {
        loadDateInRange()
    }

    fun getToday(): DateItem = convertLocalDateToDateItem(today)

    fun updateDate(newDate: DateItem) {
        _date.value = newDate
    }

    fun loadDateInRange() {
        val inicio = LocalDate.now().minusMonths(3)
        val fin = LocalDate.now().plusMonths(3)
        _dateInRange.value = getDayBetween(inicio, fin)
    }

    private fun getDayBetween(
        inicio: LocalDate,
        fin: LocalDate,
    ): List<DateItem> {
        val datesBetweenStarAndEnd = mutableListOf<DateItem>()
        var fechaActual = inicio
        while (!fechaActual.isAfter(fin)) {
            datesBetweenStarAndEnd.add(convertLocalDateToDateItem(fechaActual))
            fechaActual = fechaActual.plusDays(1)
        }
        return datesBetweenStarAndEnd
    }

    private fun convertLocalDateToDateItem(fecha: LocalDate): DateItem =
        DateItem(fecha.dayOfMonth, fecha.month.value, fecha.year, fecha.dayOfWeek.convertToDIADELASEMANA(fecha.dayOfWeek))

    fun returnTodayDateInRange(): DateItem {
        val datefind: DateItem =
            dateInRange.value.find { date ->
                date.day == today.dayOfMonth &&
                    date.month == today.month.value &&
                    date.year == today.year &&
                    date.dayOfWeek == today.dayOfWeek.convertToDIADELASEMANA(today.dayOfWeek)
            }!!
        return datefind
    }


    fun filterForType(filter: String) {
        //TODO
    }

    fun filterForCategory(filter: String) {
        //TODO
    }

    fun searchHabits(search: String) {

    }

    ///fun obtener las categorias desde los habitos del usuario

}
