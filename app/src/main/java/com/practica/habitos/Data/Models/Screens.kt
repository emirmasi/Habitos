package com.practica.habitos.Data.Models // ktlint-disable package-name

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.practica.habitos.R

sealed class Screens(
    val route: String,
    val icon: ImageVector,
) {
    object Hoy : Screens("hoy",Icons.Default.Call)
    object Habits : Screens("habitos", Icons.Default.Favorite)
    object Categories : Screens("categorias", Icons.Default.AddCircle)
}

///ayer el manchester united le gano al aston villa
