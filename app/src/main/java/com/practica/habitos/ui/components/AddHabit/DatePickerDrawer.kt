package com.practica.habitos.ui.components.AddHabit

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.practica.habitos.Domain.Models.DateItem
import com.practica.habitos.ui.screen.addHabitScreen.AddHabitViewModels
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDrawer(
    viewModels: AddHabitViewModels,
    openDialog: Boolean,
    onDissmissButton: () -> Unit,
    selectedDate: (DateItem) -> Unit,
    alertDialog: @Composable (MutableState<Boolean>) -> Unit,
    isFechaDeInicio: Boolean
) {
    var openAlertDialog = remember {
        mutableStateOf(false)
    }
    val state = rememberDatePickerState(null)

    if (openDialog) {
        DatePickerDialog(
            onDismissRequest = { onDissmissButton() },
            confirmButton = {
                Button(onClick = {
                    val date = state.selectedDateMillis
                    date?.let {
                        val instance: LocalDate =
                            Instant.ofEpochMilli(date).atZone(ZoneId.of("UTC")).toLocalDate()

                        if (isFechaDeInicio) {
                            val currentDate = LocalDate.now()
                            if (instance >= currentDate &&
                                viewModels.fechaDeInicioEsMayorQueFechaDeFin(
                                    viewModels.parseDateLocalDateToDateItem(instance)
                                )
                            ) {
                                selectedDate(viewModels.parseDateLocalDateToDateItem(instance))
                                onDissmissButton()
                            } else {
                                openAlertDialog.value = true
                            }
                        } else if (viewModels.fechaDeFinEsMayorQueFechaDeInicio(
                                viewModels.parseDateLocalDateToDateItem(instance)
                            )
                        ) {
                            selectedDate(viewModels.parseDateLocalDateToDateItem(instance))
                            onDissmissButton()
                        } else {
                            openAlertDialog.value = true
                        }
                    }
                }) {
                    Text(text = "Confirmar")
                }
            }
        ) {
            DatePicker(
                state = state
            )
        }
    }

    LaunchedEffect(openAlertDialog) {
        if (!openAlertDialog.value) {
            // Restablecer openAlertDialog después de cerrar el AlertDialog
            openAlertDialog.value = false
        }
    }

    if (openAlertDialog.value) {
        alertDialog(openAlertDialog)
    }
}