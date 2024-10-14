package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.practica.habitos.domain.models.UserHabitLog

@Composable
fun HabitList(
    listOfHabits: State<List<UserHabitLog>>,
    navController: NavHostController,
) {
    LazyColumn(
        modifier =
        Modifier
            .fillMaxHeight(),
    ) {
        items(listOfHabits.value.size) { index ->
            listOfHabits.value[index].habito.forEach { date ->
                ItemCard(
                    icon = {
                        CategoryIcon(
                            icon = date.categoria.icono,
                            contentDescription = date.categoria.nombre,
                            color = date.categoria.color,
                        )
                    },
                    content = {
                        HabitContent(
                            habito = date,
                        )
                    },
                    action = {
                        HabitAction {
                        }
                    },
                ) {
                    //todo: navegar al detalles de la actividad

                }
            }
        }
    }
}