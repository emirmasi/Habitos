package com.practica.habitos.ui.components.habitsScreenComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.practica.habitos.domain.models.Categoria
import com.practica.habitos.domain.models.DIASDELASEMANA

@Composable
fun TopCarHabit(
    title: String,
    categoria: Categoria,
    diasARealizarloDeLaSemana: Set<DIASDELASEMANA>,
    navController: NavController,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween, // Para colocar los elementos en los extremos
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier =
            Modifier
                .weight(1f),
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            // /este text deberia hacer de una forma
            ShowDays(
                diasARealizarloDeLaSemana = diasARealizarloDeLaSemana,
                color = categoria.color,
            )
        }
        Icon(
            imageVector = categoria.icono,
            contentDescription = null,
            tint = categoria.color,
        )
    }
}