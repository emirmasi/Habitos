package com.practica.habitos.domain.models

import androidx.annotation.DrawableRes
import com.practica.habitos.R

sealed class NavigationRoutes(
    val route: String,
    @DrawableRes val icon: Int,
    val title: String,
) {
    object Hoy : NavigationRoutes(route = "TodayScreen", R.drawable.hoy_habits, title = "Hoy")

    object Habits : NavigationRoutes(route = "HabitScreen", R.drawable.habits_24, title = "Habitos")

    object Categories : NavigationRoutes(route = "CategoriesScreen", R.drawable.categories, title = "Categorias")

    object Timer : NavigationRoutes(route = "TimerScreen", R.drawable.baseline_timer_24, title = "Timer")

    object Personalize: NavigationRoutes(route = "PersonalizeScreen", R.drawable.personalizar_24, title = "Personalizar")

    object Setting: NavigationRoutes(route = "SettingScreen", R.drawable.setting_24, title = "Ajustes")

    object CopySegurity: NavigationRoutes(route = "Backup", R.drawable.copysegurity, title = "Copia de Seguridad")

    object Premium: NavigationRoutes(route = "Obtener premium", R.drawable.premium, title = "Obtener premium")
    object Qualify: NavigationRoutes(route = "Qualify", R.drawable.calificar, title = "Calificar")

    object ContactUs: NavigationRoutes(route = "ContactUs",R.drawable.contact_page_24, title = "Contáctanos")
}
