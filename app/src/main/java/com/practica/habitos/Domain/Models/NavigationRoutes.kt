package com.practica.habitos.Domain.Models

import androidx.annotation.DrawableRes
import com.practica.habitos.R

sealed class NavigationRoutes(
    val route: String,
    @DrawableRes val icon: Int,
) {
    object Hoy : NavigationRoutes("hoy", R.drawable.hoy_habits)

    object Habits : NavigationRoutes("habitos", R.drawable.habits_24)

    object Categories : NavigationRoutes("categorias", R.drawable.categories)

    object Timer : NavigationRoutes("timer", R.drawable.baseline_timer_24)
}
