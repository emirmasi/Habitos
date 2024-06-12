package com.practica.habitos.ui.theme.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.practica.habitos.Data.Models.DateItem
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.IconColor
import com.practica.habitos.ui.theme.Rosadito
import com.practica.habitos.ui.theme.Screen.HoyScreen.HoyScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@ExperimentalMaterial3Api
@Composable
fun TopBar(
    drawerState: DrawerState,
    scope:CoroutineScope,
    viewModel: HoyScreenViewModel
){
    val state = rememberDatePickerState()
    var showDialog by remember{
        mutableStateOf(false)
    }


    TopAppBar(
        title = {
            Text(
                text = "${viewModel.hoy.value?.toConvert()}",
                color = com.practica.habitos.ui.theme.Text
            )
                },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }
            ) {
                Icon(
                    Icons.Outlined.Menu,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp),
                    tint = Rosadito
                )
            }
        },
        actions = {
            //aca va el calendario
            IconButton(
                onClick = {
                    showDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = "calendario",
                    modifier = Modifier.size(40.dp),
                    tint = IconColor
                )
                if(showDialog){
                    DatePickerDialog(
                        onDismissRequest = {showDialog = false},
                        confirmButton = {
                            Button(onClick = {
                                showDialog = false
                                val date = state.selectedDateMillis
                                date?.let {
                                    val instance: LocalDate = Instant.ofEpochMilli(date).atZone(
                                        ZoneId.of("UTC")).toLocalDate()
                                    viewModel.actualizarHoy(
                                        DateItem(
                                            instance.dayOfMonth,
                                            instance.month.value,
                                            instance.year,
                                            instance.dayOfWeek
                                        )
                                    )
                                }
                            }
                            ) {
                                Text(text = "Confirmar")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showDialog = false }
                            ) {
                                Text(text = "Cerrar")
                            }
                        }
                    ) {
                        DatePicker(
                            state = state
                        )
                    }
                }


            }

        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = BackgroundHoyScree
        )
    )
}

