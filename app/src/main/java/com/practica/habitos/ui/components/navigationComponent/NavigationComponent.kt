package com.practica.habitos.ui.components.navigationComponent

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practica.habitos.domain.models.NavigationRoutes
import com.practica.habitos.ui.screen.categoryScreen.CategoriesScreenContent
import com.practica.habitos.ui.screen.habitsScreen.HabitsScreenContent
import com.practica.habitos.ui.screen.hoyScreen.HoyScreenContent
import com.practica.habitos.ui.screen.hoyScreen.HoyScreenViewModel

@Composable
fun NavigationComponent(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController ,
        startDestination = NavigationRoutes.Hoy.route,
    ){
        composable(NavigationRoutes.Hoy.route){
            HoyScreenContent(navHostController, HoyScreenViewModel())
        }
        composable(NavigationRoutes.Habits.route){
            HabitsScreenContent()
        }
        composable(NavigationRoutes.Categories.route){
            CategoriesScreenContent(navController = navHostController)
        }
    }
}
