package com.practica.habitos.ui.components.navigationComponent

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.practica.habitos.Domain.Models.NavigationRoutes

@Composable
fun BottomBar(navController: NavHostController) {
    // está obteniendo la entrada actual en la pila de retroceso de la navegación
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val menuItems =
        listOf(
            NavigationRoutes.Hoy,
            NavigationRoutes.Habits,
            NavigationRoutes.Categories,
            NavigationRoutes.Timer,
        )

    BottomAppBar(
        contentColor = MaterialTheme.colorScheme.secondary,
    ) {
        NavigationBar(
            contentColor = MaterialTheme.colorScheme.secondary,
        ) {
            menuItems.forEach { item ->
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = { navController.navigate(item.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "",
                        )
                    },
                    label = {
                        Text(
                            text = item.route,
                        )
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(navController = NavHostController(LocalContext.current))
}
