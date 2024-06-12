package com.practica.habitos.ui.theme.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.practica.habitos.Data.Models.Screens
import com.practica.habitos.ui.theme.BackgrounBottomBar
import com.practica.habitos.ui.theme.IconColor

@Composable
fun BottomBar(
    navController: NavHostController
){
    //está obteniendo la entrada actual en la pila de retroceso de la navegación
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val menu_items = listOf(
        Screens.Hoy,
        Screens.Habits,
        Screens.Categories
    )

    BottomAppBar(
        containerColor = BackgrounBottomBar,
        contentColor = BackgrounBottomBar
    ) {
        NavigationBar(
            containerColor = BackgrounBottomBar
        ) {
            menu_items.forEach {item->
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = { navController.navigate(item.route) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = "",
                            tint = IconColor
                        )
                    },
                    label = { Text(
                        text = item.route,
                        color = Color.White
                    )}
                )
            }

        }
    }

}