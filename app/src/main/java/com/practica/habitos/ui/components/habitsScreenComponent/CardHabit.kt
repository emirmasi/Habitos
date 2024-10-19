package com.practica.habitos.ui.components.habitsScreenComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.domain.models.Categoria
import com.practica.habitos.domain.models.DIASDELASEMANA
import com.practica.habitos.domain.models.DateItem
import com.practica.habitos.domain.models.Habito
import com.practica.habitos.ui.theme.onTertiaryLight
import com.practica.habitos.ui.theme.secondaryLight

@Composable
fun HabitCard(
    habito: Habito,
    navController: NavController,
    semana: State<List<DateItem>>,
    onHabitoClick: () -> Unit,
) {

    ///la semana lo tengo que poner a partir de la fecha de inicio + 7 dias
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
        CalendarItemHabitScreen(
            semana = semana,
            diasARealizarloDeLaSemana = habito.diasARealizarloDeLaSemana,
        )
    }
}

// /este componente deberia mostrar los dias de la semana que va a realizar el habito
// /muestra solo 7 dias de la semana a partir de la fecha actual
@Composable
fun CalendarItemHabitScreen(
    semana: State<List<DateItem>>,
    diasARealizarloDeLaSemana: Set<DIASDELASEMANA>,
) {
    var backgroundColor by remember { mutableStateOf(onTertiaryLight) }
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        semana.value.forEach { date ->
            // /realizamos la card
            Card(
                modifier =
                    Modifier
                        .height(62.dp)
                        .width(50.dp)
                        .padding(1.dp),
                shape = RoundedCornerShape(20.dp),
            ) {
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .height(30.dp)
                                .background(
                                    if (diasARealizarloDeLaSemana.contains(date.dayOfWeek)) {
                                        backgroundColor
                                    } else {
                                        MaterialTheme.colorScheme.primaryContainer
                                    },
                                ),
                    ) {
                        Text(
                            text = "${date.dayOfWeek.toString().subSequence(0, 3)}",
                            color = secondaryLight,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Text(
                            text = "${date.day}",
                            fontSize = 20.sp,
                            color = secondaryLight,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
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
            fechaDeInicio = DateItem(day = 1, month = 1, year = 2023, dayOfWeek = DIASDELASEMANA.LUNES),
            fechaDeFin = DateItem(day = 7, month = 1, year = 2023, dayOfWeek = DIASDELASEMANA.LUNES),
            diasARealizarloDeLaSemana = setOf(DIASDELASEMANA.MARTES, DIASDELASEMANA.JUEVES),
        )

    ///tengo que poner una semana para ver el preview
    HabitCard(
        habito = habito,
        navController = rememberNavController(),
        semana = remember {
            mutableStateOf(
                listOf(
                    DateItem(day = 1, month = 1, year = 2023, dayOfWeek = DIASDELASEMANA.LUNES),
                    DateItem(day = 2, month = 1, year = 2023, dayOfWeek = DIASDELASEMANA.MARTES),
                    DateItem(day = 3, month = 1, year = 2023, dayOfWeek = DIASDELASEMANA.MIERCOLES),
                    DateItem(day = 4, month = 1, year = 2023, dayOfWeek = DIASDELASEMANA.JUEVES),
                    DateItem(day = 5, month = 1, year = 2023, dayOfWeek = DIASDELASEMANA.VIERNES),
                    DateItem(day = 6, month = 1, year = 2023, dayOfWeek = DIASDELASEMANA.SABADO),
                    DateItem(day = 7, month = 1, year = 2023, dayOfWeek = DIASDELASEMANA.DOMINGO)
                )) },
        onHabitoClick = {},
    )
        
}
