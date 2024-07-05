package com.practica.habitos.ui.components.navigationComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.practica.habitos.Domain.Models.NavigationRoutes
import com.practica.habitos.Domain.utils.currentRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuLateral(
    navController: NavHostController,
    drawerdState: DrawerState,
    scope: CoroutineScope,
    content : @Composable () -> Unit
){
    val menuItems = listOf(
        NavigationRoutes.Hoy,
        NavigationRoutes.Habits,
        NavigationRoutes.Categories,
        NavigationRoutes.Timer
    )

    ModalNavigationDrawer(
        drawerState = drawerdState,
        drawerContent = {
            ModalDrawerSheet{
                /*aca va a ir un logo de la app */
                menuItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                                Icon(painter = painterResource(id = item.icon), contentDescription = null)
                            },
                        label = { Text(text = item.route) },
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
    ) {
        content()
    }
}

@Composable
fun NavigationDrawerItem(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .padding(all = 5.dp)
            .clickable { onClick() }
            .background(if (selected) Color.Gray else Color.Transparent)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            icon()
            label()
        }
    }
}
