package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.practica.habitos.domain.models.DateItem
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent(
    onDissmiss: () -> Unit,
    onDateSelected: (DateItem) -> Unit,
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = { onDissmiss() },
        confirmButton = {
            Button(
                onClick = {
                    val date = datePickerState.selectedDateMillis
                    date?.let {
                        val instance: LocalDate =
                            Instant
                                .ofEpochMilli(date)
                                .atZone(
                                    ZoneId.of("UTC"),
                                ).toLocalDate()

                        val dateSelected = DateItem(
                            instance.dayOfMonth,
                            instance.month.value,
                            instance.year,
                            instance.dayOfWeek,
                        )
                        onDateSelected(dateSelected)
                    }
                    onDissmiss()
                },
            ) {
                Text(text = "Confirmar")
            }
        },
        dismissButton = {
            Button(
                onClick = { onDissmiss() },
            ) {
                Text(text = "Cerrar")
            }
        },
    ) {
        DatePicker(
            state = datePickerState,
        )
    }
}