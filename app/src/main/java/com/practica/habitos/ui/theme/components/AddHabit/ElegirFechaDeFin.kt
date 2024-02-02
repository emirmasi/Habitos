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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FechaDeFin(viewModels: AddHabitViewModels) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    val openAlertDialog  = remember{
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
                    contentDescription = "elegir fecha de fin",
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
                    text = "Fecha de fin",
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
                        if (viewModels.selectedFechaDeFin.value == null)
                            BackgroundHoyScree
                        else
                            RosaditoMasClaro
                    ),
                contentAlignment = Alignment.CenterEnd
            ){
                Text(
                    text = if(viewModels.selectedFechaDeFin.value != null) "${viewModels.selectedFechaDeFin.value?.toConvert()}" else "",
                    fontWeight = FontWeight.Bold,
                    fontSize= 18.sp ,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
    DatePickerWrapper(
        viewModels = viewModels,
        openDialog = openDialog,
        onDissmissButton = {openDialog = false},
        selectedDate = {
            if(viewModels.fechaDeFinEsMayorQueFechaDeInicio(DateItem(it.dayOfMonth,it.month.value,it.year,it.dayOfWeek))){
                viewModels.setSelectedFechaDeFin(DateItem(it.dayOfMonth,it.month.value,it.year,it.dayOfWeek))
            }else{
                openAlertDialog.value = true
                openDialog = false
            }

        }
    )
    MyAlertDialogForFechaDeFin1(
        openAlertDialog = openAlertDialog.value
    ){
        openAlertDialog.value = false
    }
}

@Composable
fun MyAlertDialogForFechaDeFin1(
    openAlertDialog:Boolean,
    onDissmisButton: ()->Unit
){
    if(openAlertDialog){
        AlertDialog(
            onDismissRequest = { onDissmisButton() },
            confirmButton = {
                    Button(onClick = { onDissmisButton() }) {
                        Text(text = "Confirmar")
                    }
                 },
            modifier = Modifier
                .height(300.dp)
                .width(500.dp)
                .background(Color.Black),
            text = { Text(
                text = "La fecha de fin debe ser menor a la de fecha de inicio",
                fontSize = 18.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold
                )
            }
        )

    }
}