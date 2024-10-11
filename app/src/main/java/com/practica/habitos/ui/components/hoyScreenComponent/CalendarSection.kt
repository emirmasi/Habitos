package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.practica.habitos.domain.models.DateItem

///todo: este componente se encarga de cambiar la fecha de calendario y devolver la fecha elegido

@Composable
fun CalendarSection(
    dateInRange: State<List<DateItem>>,
    returnTodayDateInRage: () -> DateItem,
    actualizarHoy: (DateItem) -> Unit,
) {
    CalendarItem(
        dateInRange = dateInRange,
        returnTodayDateInRange = { returnTodayDateInRage() },
    ) { dateItem ->
        actualizarHoy(dateItem)
    }
}