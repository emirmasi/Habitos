package com.practica.habitos.ui.components.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.R
import com.practica.habitos.domain.models.Categoria

// /todo: deberiamos restringir la cantidad de categorias, en el caso de que no tenga categorias debo mostrar un
@Composable
fun CategoriesCustom(
    listOfCategories: List<Categoria>,
    isPremium: Boolean,
) {
    // /todo: puedo utlizar el categoria display tambien aca
    var cantDeCategoriasPersonalizables = if (isPremium) 20 else 5
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp),
    ) {
        Text(
            text = "Categorias personalizadas",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = "${cantDeCategoriasPersonalizables - listOfCategories.size} disponibles ",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        )
        if (listOfCategories.isEmpty()) {
            //todo: mostrar algo x defecto  
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(painter = painterResource(id = R.drawable.categories), contentDescription = null)
                Text(text = "No hay categorias personalizadas")
            }
        } else {
            CategoryDisplay(categories = listOfCategories) {
                ///todo: en este caso no hago nada con la seleccionada
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesCustomPreview() {
    val categoria = Categoria(
        nombre = "Deporte",
        icono = androidx.compose.material.icons.Icons.Default.Settings,
        color = Color.Red
    )
    CategoriesCustom(
        listOfCategories = listOf(categoria, categoria, categoria, categoria),
        isPremium = false
    )
}
