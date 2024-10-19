package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica.habitos.domain.models.Categoria
import com.practica.habitos.domain.models.DIASDELASEMANA
import com.practica.habitos.domain.models.DateItem
import com.practica.habitos.domain.models.Habito

///todo:mejorar , mostrar icono , titulo , y una animacion para ver mas detalles , descripcion , fecha de inicio y de fin y ademas boton para eliminar y editar
@Composable
fun ItemCard(
    icon: @Composable() (() -> Unit),
    content: @Composable() (() -> Unit),
    action: @Composable() (() -> Unit),
    onClick: () -> Unit,
) {
    // hay que declarar una variable is expanded
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .height(56.dp),
                verticalAlignment = Alignment.CenterVertically,
        ) {
            // /como hago para la alineacion?
            icon()
            content()
            Spacer(modifier = Modifier.weight(1f))
            action()
        }
        HorizontalDivider()
    }
}

@Composable
fun HabitContent(
    habito: Habito,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = habito.habito,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun HabitAction(
    isCompleted: (Boolean?)->Unit,
) {
    Box(
        modifier = Modifier.padding(end = 8.dp),
    ){
        CircleClickable(
            isPaintWithColor = false,
        ){
            circleSelected->
            isCompleted(circleSelected)
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
       fechaDeInicio = DateItem(10,6,2024,DIASDELASEMANA.DOMINGO),
       fechaDeFin = DateItem(12,6,2024,DIASDELASEMANA.JUEVES),
       diasARealizarloDeLaSemana = setOf(DIASDELASEMANA.MIERCOLES),
        completed = true,
    )
    ItemCard(
        icon = {
            CategoryIcon(
                icon = habito.categoria.icono,
                contentDescription = habito.categoria.nombre,
                color = habito.categoria.color,
            )
        },
        content = {
            HabitContent(
                habito = habito,
                modifier = Modifier,
            )
        },
        action = {
            HabitAction{

            }
        }
    ) {

    }
}
