package com.practica.habitos.ui.theme.components.AddHabit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.Data.Models.DateItem
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.Rosadito
import com.practica.habitos.ui.theme.RosaditoMasClaro
import com.practica.habitos.ui.theme.Screen.AddHabitScreen.AddHabitViewModels
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FechaDeInico(viewModels: AddHabitViewModels) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            .clickable {
                openDialog = true
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(3.dp, color = Rosadito, shape = RoundedCornerShape(16.dp))
                .background(BackgroundHoyScree),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ///aca el icono del date y el clickeable
            Box(
                modifier = Modifier
                    .padding(2.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "elegir fecha de inicio",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 3.dp)
                        .clickable {
                            openDialog = true
                        },
                    tint = Rosadito
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
            ){
                Text(
                    text = "Fecha de inicio",
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                    )

            }
            ///aca va el box con el texto
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(40.dp)
                    .padding(end = 10.dp)
                    .border(2.dp, color = BackgroundHoyScree, shape = RoundedCornerShape(6.dp))
                    .background(
                        if (viewModels.selectedFechaDeInicio.value == null)
                            BackgroundHoyScree
                        else
                            RosaditoMasClaro
                    ),
                contentAlignment = Alignment.CenterEnd
            ){
                Text(
                    text = if(viewModels.selectedFechaDeInicio.value != null) "${viewModels.selectedFechaDeInicio.value?.toConvert()}" else "",
                    fontWeight = FontWeight.Bold,
                    fontSize= 18.sp ,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            }
            ///esto deberia ser una funcio aparte
        }
    }
    DatePickerWrapper(
        viewModels = viewModels,
        openDialog = openDialog,
        onDissmissButton = {openDialog = false},
        selectedDate = {viewModels.setSelectedFechaDeInicio(DateItem(it.dayOfMonth,it.month.value,it.year,it.dayOfWeek))}
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWrapper(
    viewModels: AddHabitViewModels,
    openDialog: Boolean,
    onDissmissButton: ()->Unit,
    selectedDate: (LocalDate)->Unit
){
    val state = rememberDatePickerState(null)
    var selectedDateState by remember { mutableStateOf(LocalDate.now()) } // Estado interno para mantener la fecha seleccionada
    LaunchedEffect(openDialog) {
        if (openDialog) {
            selectedDateState = null // Restablecer la fecha seleccionada cuando el diálogo se abre
        }
    }

    if(openDialog){
        DatePickerDialog(
            onDismissRequest = { onDissmissButton() },
            confirmButton = {
                Button(onClick = { onDissmissButton() }) {
                    Text(text = "Confirmar")
                }
            }
        ) {
            DatePicker(
                state = state
            )
            val date = state.selectedDateMillis
            date?.let {
                val instance: LocalDate = Instant.ofEpochMilli(date).atZone(
                    ZoneId.of("UTC")).toLocalDate()
                selectedDateState = instance
                selectedDate(selectedDateState)
            }
        }
    }
}