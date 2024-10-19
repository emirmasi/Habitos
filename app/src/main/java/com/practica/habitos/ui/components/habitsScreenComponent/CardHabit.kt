package com.practica.habitos.ui.components.habitsScreenComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.domain.models.Categoria
import com.practica.habitos.domain.models.DIASDELASEMANA
import com.practica.habitos.domain.models.DateItem
import com.practica.habitos.domain.models.Habito

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
            .height(170.dp)
            .fillMaxWidth(),
    ) {
        ///otro componente?
        TopCarHabit(
            title = habito.habito,
            categoria = habito.categoria,
            diasARealizarloDeLaSemana = habito.diasARealizarloDeLaSemana,
            navController = navController
        )
        ///deberia haber un espacio
        CalendarItemHabitScreen(
            semana = semana,
            color = habito.categoria.color,
            diasARealizarloDeLaSemana = habito.diasARealizarloDeLaSemana,
        )
        HorizontalDivider(modifier = Modifier.padding(top = 10.dp))
        ///componente para las estadisticas y editar habito

        BottomCardHabit(navController = navController)
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
