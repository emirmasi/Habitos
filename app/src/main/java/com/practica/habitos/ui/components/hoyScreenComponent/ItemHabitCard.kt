package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica.habitos.domain.models.Categoria
import com.practica.habitos.domain.models.DIASDELASEMANA
import com.practica.habitos.domain.models.DateItem
import com.practica.habitos.domain.models.Habito
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.IconCategories
import java.time.DayOfWeek

///todo:mejorar , mostrar icono , titulo , y una animacion para ver mas detalles , descripcion , fecha de inicio y de fin y ademas boton para eliminar y editar
@Composable
fun ItemCard(
    habito: Habito
) {
    // hay que declarar una variable is expanded
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)
            .clickable {
                       ///deberiamos levarnos a una pantalla para editar o eliminar el habito
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(BackgroundHoyScree),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment =  Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ){
                Icon(
                    imageVector = habito.categoria.icono,
                    contentDescription = habito.habito,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 3.dp , vertical = 3.dp)
                        .background(habito.categoria.color),
                    tint = IconCategories
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = habito.habito,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
            Box(
                modifier = Modifier
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ){
                CircleWithArrowAndCross(habito)
                ///aca hay que llevar el habito devuelta para ver si se modifico
            }

        }
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun ItemCardPreview(){
   val habito = Habito(
       habito = "comer bien",
       descripcion = "comer manzana",
       categoria = Categoria("ejer", Icons.Default.Home, Color.Blue),
       fechaDeInicio = DateItem(10,6,2024,DayOfWeek.SUNDAY),
       fechaDeFin = DateItem(12,6,2024,DayOfWeek.WEDNESDAY),
       diasARealizarloDeLaSemana = setOf(DIASDELASEMANA.MIERCOLES),
       completed = true
       )
    
    ItemCard(habito = habito)
}
