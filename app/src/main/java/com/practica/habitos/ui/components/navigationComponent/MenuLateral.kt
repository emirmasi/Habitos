package com.practica.habitos.ui.components.navigationComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
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
    content: @Composable () -> Unit,
)  {
    val menuItems = listOf(
        NavigationRoutes.Hoy,
        NavigationRoutes.Categories,
        NavigationRoutes.Timer,
        NavigationRoutes.Personalize,
        NavigationRoutes.Setting,
        NavigationRoutes.CopySegurity,
        NavigationRoutes.Premium,
        NavigationRoutes.Qualify,
        NavigationRoutes.ContactUs,
    )
    ModalNavigationDrawer(
        drawerState = drawerdState,
        drawerContent = {
            ModalDrawerSheet{
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
                        text = today.convertDayofWeekToString(today.dayOfWeek),
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
                    var count = 0
                    menuItems.forEach { item ->
                        count++
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = null,
                                )
                            },
                            label = {
                                Text(
                                    text = item.title.lowercase(),
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            },
                            selected = currentRoute(navController = navController) == item.route,
                            onClick = {
                                scope.launch {
                                    drawerdState.close()
                                }
                                navController.navigate(item.route)
                            }
                        )
                        if(count == 3){
                            HorizontalDivider()
                            count = 0
                        }
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
