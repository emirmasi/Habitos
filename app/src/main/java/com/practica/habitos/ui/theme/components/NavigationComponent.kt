package com.practica.habitos.ui.theme.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practica.habitos.Data.Models.Screens
import com.practica.habitos.ui.theme.Screen.CategoryScreen.CategoriesScreenContent
import com.practica.habitos.ui.theme.Screen.HabitsScreen.HabitsScreenContent
import com.practica.habitos.ui.theme.Screen.HoyScreen.HoyScreenContent
import com.practica.habitos.ui.theme.Screen.HoyScreen.HoyScreenViewModel

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
