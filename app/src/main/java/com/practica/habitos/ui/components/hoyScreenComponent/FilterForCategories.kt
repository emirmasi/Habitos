package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterForCategories(
    categories: List<String>,
    onCategorySelected: (String) -> Unit
){
    val openDialog = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(BorderStroke(1.dp, Color.Black))
            .clickable {
                //todo: componenente para seleccionar una categoria
                openDialog.value = true
            },
        contentAlignment = Alignment.Center
    ){
        ///esto es un listPreview
        Text(
            text = "Selecciona una categoria",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
    if (openDialog.value){
        //todo: componente para seleccionar una categoria
        AlertDialogForCategories(onDismissRequest = { openDialog.value = false}, categories = categories) {
            
        }
    }
}

@Composable
fun AlertDialogForCategories(
    onDismissRequest: () -> Unit,
    categories: List<String>,
    onCategorySelected: (String) -> Unit
){
    AlertDialog(
        onDismissRequest = { onDismissRequest },
        confirmButton = { /*TODO*/ }
    )
}
