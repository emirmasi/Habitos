package com.practica.habitos.ui.screen.hoyScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.ui.components.HoyScreenComponent.CalendarItem
import com.practica.habitos.ui.components.HoyScreenComponent.ItemCard
import com.practica.habitos.ui.components.navigationComponent.AppBar
import com.practica.habitos.ui.components.navigationComponent.MenuLateral

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoyScreenContent(
    navController: NavHostController,
    viewModel: HoyScreenViewModel,
) {
    // primer error pasarle el viewModel por parametro
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    /*tengo que enviarle el dia de hoy*/
    val today = viewModel.getToday()
    MenuLateral(
        navController = navController,
        drawerdState = drawerState,
        scope = scope,
        today = today,
    ) {
        Scaffold(
            topBar = {
                AppBar(
                    drawerState = drawerState,
                    scope = scope,
                    viewModel.date,
                ) { dateItem ->
                    viewModel.updateDate(dateItem)
                }
            },
        ) { paddingValues ->
            Column(
                modifier =
                    Modifier
                        .padding(paddingValues),
            ) {
                // /hecho
                CalendarItem(
                    dateInRange = viewModel.dateInRange,
                    returnTodayDateInRage = { viewModel.returnTodayDateInRange() },
                    actualizarHoy = { dateItem -> viewModel.updateDate(dateItem) },
                )
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
                LazyColumn(
                    modifier =
                        Modifier
                            .fillMaxHeight(),
                ) {
                    items(viewModel.habitos.value.size) { index ->
                        viewModel.habitos.value[index].habito.forEach { date ->
                            ItemCard(habito = date)
                        }
                    }
                }
            }
        }
    }
}

// /esto lo puedo mejorar con un checkbox de material 3

@Preview(showBackground = true)
@Composable
fun ItemsPreview() {
    val navController = rememberNavController()
    HoyScreenContent(navController = navController, HoyScreenViewModel())
}
