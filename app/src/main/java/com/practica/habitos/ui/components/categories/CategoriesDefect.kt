package com.practica.habitos.ui.components.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.domain.models.Categoria


//todo: en este caso solo debe mostrar las categorias por defecto
@Composable
fun CategoriesForDefect(
    listOfCategoriesForDefect: List<Categoria>,
    isPremium: Boolean,
    categoryCustom: (Categoria)->Unit,
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 5.dp)
    ) {
        Text(
            text = "Categorias por defecto",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
            )
        Text(
            text = "Editable para usuarios premium",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
        CategoryDisplay(categories = listOfCategoriesForDefect) {
            if(isPremium) categoryCustom(it)//todo: si premium es true entonces debo devolver una categoria para editar
        }
    }
}
