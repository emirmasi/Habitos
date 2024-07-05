package com.practica.habitos.ui.components.navigationComponent

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practica.habitos.Domain.Models.Screens
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
        startDestination = Screens.Hoy.route,
    ){
        composable(Screens.Hoy.route){
            HoyScreenContent(navHostController, HoyScreenViewModel())
        }
        composable(Screens.Habits.route){
            HabitsScreenContent()
        }
        composable(Screens.Categories.route){
            CategoriesScreenContent()
        }
    }
}
