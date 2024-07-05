package com.practica.habitos.Data.Models // ktlint-disable package-name

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.sharp.Star
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screens(
    val route: String,
    val icon: ImageVector,
) {
    object Hoy : Screens("hoy", Icons.Filled.List)
    object Habits : Screens("habitos", Icons.Default.Person)
    object Categories : Screens("categorias",Icons.Sharp.Star)
}

///ayer el manchester united le gano al aston villa
