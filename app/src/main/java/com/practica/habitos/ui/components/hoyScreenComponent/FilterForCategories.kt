package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.domain.models.Categoria

@Composable
fun FilterForCategories(
    categories: List<Categoria>,
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
        AlertDialogForCategories(
            onDismissRequest = { openDialog.value = false },
            categories = categories,
        ) { catSelected ->
            onCategorySelected(catSelected)
        }
    }
}

@Composable
fun AlertDialogForCategories(
    onDismissRequest: () -> Unit,
    categories: List<Categoria>,
    onCategorySelected: (String) -> Unit
){
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = { Button(onClick = { /*TODO*/ }) {
                Text(text = "Borrar seleccion")
            }
        },
        dismissButton = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Cerrar")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        title = { Text(text = "Selecciona una categoria") },
        text = {
            LazyColumn(
                modifier = Modifier.padding(bottom = 5.dp)
            ) {
                items(categories) { item ->
                    ItemCard(
                        icon = {
                            CategoryIcon(
                                icon = item.icono,
                                contentDescription = item.nombre,
                                color = item.color
                            )
                        },
                        content = {
                            Text(
                                text = item.nombre,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                            )
                        },
                        action = {
                            CircleClickable(isPaintWithColor = true) {
                                select->
                                if(select == true){
                                    onCategorySelected(item.nombre)
                                }
                            }
                        },
                    ) {
                    }
                    HorizontalDivider()
                }
            }
        },
        shape = RoundedCornerShape(20.dp),

    )
}

@Preview
@Composable
fun DialogPreview() {
    val categoria = Categoria(
        nombre = "categoria",
        icono = Icons.Default.Home,
        color = Color.Black
    )
    val categoria2 = Categoria(
        nombre = "categoria2",
        icono = Icons.Default.Home,
        color = Color.Black
    )
    AlertDialogForCategories(
        onDismissRequest = { /*TODO*/ },
        categories = listOf(categoria, categoria2)
    ) {

    }
}
