package com.practica.habitos.ui.components.habitsScreenComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.domain.models.Categoria
import com.practica.habitos.domain.models.DIASDELASEMANA
import com.practica.habitos.domain.models.DateItem
import com.practica.habitos.domain.models.Habito
import java.time.DayOfWeek

@Composable
fun HabitCard(
    habito: Habito,
    onHabitoClick: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .height(200.dp)
                .fillMaxWidth(),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween, // Para colocar los elementos en los extremos
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
            ) {
                Text(
                    text = habito.habito,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                // /este text deberia hacer de una forma
                ShowDays(
                    diasARealizarloDeLaSemana = habito.diasARealizarloDeLaSemana,
                    color = habito.categoria.color,
                )
            }
            Icon(
                imageVector = habito.categoria.icono,
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp),
                tint = habito.categoria.color,
            )
        }
    }
}


@Preview
@Composable
fun HabitCardPreview() {
    val habito =
        Habito(
            habito = "Correr",
            descripcion = "correr 5km",
            categoria =
                Categoria(
                    nombre = "fisico",
                    icono = Icons.Default.Menu,
                    color = Color.Blue,
                ),
            fechaDeInicio = DateItem(day = 1, month = 1, year = 2023, dayOfWeek = DayOfWeek.FRIDAY),
            fechaDeFin = DateItem(day = 1, month = 1, year = 2023, dayOfWeek = DayOfWeek.FRIDAY),
            diasARealizarloDeLaSemana = setOf(DIASDELASEMANA.MARTES, DIASDELASEMANA.JUEVES),
        )

    HabitCard(habito = habito, onHabitoClick = {})
}
