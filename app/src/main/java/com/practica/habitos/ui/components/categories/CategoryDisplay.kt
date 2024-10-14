package com.practica.habitos.ui.components.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica.habitos.domain.models.Categoria

///este componente sirve para seleccionar una categoria, lo saque del proyecto de la facu

@Composable
fun CategoryDisplay(
    categories: List<Categoria>,
    onCategoryClick: (Categoria) -> Unit,
) {
    var selectedCategoria by remember { mutableStateOf<Categoria?>(null) }
    Column(
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier =
            Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            items(categories) { cat ->
                Column(
                    modifier = Modifier
                        .clickable {
                            selectedCategoria = cat
                            onCategoryClick(cat)
                        }
                        .padding(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .padding(2.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = cat.icono,
                            contentDescription = cat.nombre,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(cat.color),
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = cat.nombre,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.Green,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoryDisplayPreview() {

    val categoria  = Categoria(
        nombre = "Deporte",
        icono = androidx.compose.material.icons.Icons.Default.Settings,
        color = Color.Red
    )
    CategoryDisplay(categories = listOf(categoria,categoria,categoria,categoria,categoria)) {

    }
}
