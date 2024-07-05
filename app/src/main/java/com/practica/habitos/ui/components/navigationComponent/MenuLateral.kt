package com.practica.habitos.ui.components.navigationComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.practica.habitos.Domain.Models.DIASDELASEMANA
import com.practica.habitos.Domain.Models.DateItem
import com.practica.habitos.Domain.Models.NavigationRoutes
import com.practica.habitos.Domain.utils.currentRoute
import com.practica.habitos.ui.theme.Rosadito
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuLateral(
    navController: NavHostController,
    drawerdState: DrawerState,
    scope: CoroutineScope,
    today: DateItem,
    content: @Composable () -> Unit
){
    val menuItems = listOf(
        NavigationRoutes.Hoy,
        NavigationRoutes.Habits,
        NavigationRoutes.Categories,
        NavigationRoutes.Timer
    )
    val diasDeLaSemana = mapOf(
        1 to DIASDELASEMANA.LUNES,
        2 to DIASDELASEMANA.MARTES,
        3 to DIASDELASEMANA.MIERCOLES,
        4 to DIASDELASEMANA.JUEVES,
        5 to DIASDELASEMANA.VIERNES,
        6 to DIASDELASEMANA.SABADO,
        7 to DIASDELASEMANA.DOMINGO
    )
    ModalNavigationDrawer(
        drawerState = drawerdState,
        drawerContent = {
            ModalDrawerSheet{
                /*aca va a ir un logo de la app */
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp),
                ) {
                    Text(
                        text = "HabitNow",
                        color = Rosadito,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        )
                    Text(
                        text = "${diasDeLaSemana[today.dayOfWeek.value]}",
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = today.formatDate(),
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    menuItems.forEach { item ->
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = null
                                )
                            },
                            label = { Text(
                                text = item.route.lowercase(),
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            ) },
                            selected = currentRoute(navController = navController) == item.route,
                            onClick = {
                                scope.launch {
                                    drawerdState.close()
                                }
                                navController.navigate(item.route)
                            }
                        )
                    }
                }

            }
        }
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun MenuLateralPreview(){
    Column {
        Text(
            text = "HabitNow",
            color = Rosadito,
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            )
        Text(
            text = "Viernes",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
            )
        Text(
            text = "5 de julio de 2024",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}
