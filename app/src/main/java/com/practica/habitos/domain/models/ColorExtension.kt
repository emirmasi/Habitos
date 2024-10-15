package com.practica.habitos.domain.models

import androidx.compose.ui.graphics.Color

fun Color.getLighColor(baseColor: Color, factor: Float = 0.7f):Color{
    return Color(
        red = baseColor.red + (1 - baseColor.red) * factor,
        green = baseColor.green + (1 - baseColor.green) * factor,
        blue = baseColor.blue + (1 - baseColor.blue) * factor,
        alpha = baseColor.alpha // Mantiene el alpha original
    )
}