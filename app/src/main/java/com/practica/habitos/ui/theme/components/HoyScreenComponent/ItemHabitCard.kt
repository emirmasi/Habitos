package com.practica.habitos.ui.theme.components.HoyScreenComponent

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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica.habitos.Data.Models.Categoria
import com.practica.habitos.Data.Models.DIASDELASEMANA
import com.practica.habitos.Data.Models.DateItem
import com.practica.habitos.Data.Models.Habito
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.IconCategories
import com.practica.habitos.ui.theme.Screen.HoyScreen.CircleWithArrowAndCross
import com.practica.habitos.ui.theme.Text
import java.time.DayOfWeek

///todo:mejorar , mostrar icono , titulo , y una animacion para ver mas detalles , descripcion , fecha de inicio y de fin y ademas boton para eliminar y editar
@Composable
fun ItemCard(habito: Habito) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)
            .clickable {

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
                    contentDescription = habito.descripcion,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 3.dp)
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
                    color = Text
                )
            }
            Box(
                modifier = Modifier
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ){
                CircleWithArrowAndCross(size = 30,habito)
                ///aca hay que llevar el habito devuelta para ver si se modifico
            }

        }
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
