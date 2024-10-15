package com.practica.habitos.ui.components.habitsScreenComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.domain.models.DIASDELASEMANA
import com.practica.habitos.domain.models.getLighColor

@Composable
fun ShowDays(
    diasARealizarloDeLaSemana: Set<DIASDELASEMANA>,
    color: Color,
) {
    val semana: String =
        diasARealizarloDeLaSemana
            .map { dia ->
                dia.toString().take(3).toLowerCase().replaceFirstChar { it.uppercase() }
            }.joinToString(separator = "-")

    Row {
        Text(
            text = "$semana",
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier =
            Modifier.background(
                color = color.getLighColor(color), // Color de fondo oscuro
                shape = RoundedCornerShape(2.dp),
            ),
        )
    }
}