package com.practica.habitos.ui.theme.components.AddHabit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.practica.habitos.ui.theme.Rosadito
import com.practica.habitos.ui.theme.Screen.AddHabitScreen.AddHabitViewModels

@Composable
fun ElegirDiasDeLaSemana(
    viewModel: AddHabitViewModels
){
    ///en esta funcion debo hacer un dialog donde me permita elegir los dias de la semana
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp)
    ) {
        ///el texto con la pregunta
        Text(
            text = "Elija los dias de la semana que quiera realizarlo",
            fontWeight = FontWeight.Bold,
            color = Rosadito,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        ///aca tengo que mostrar ambos opciones
        OptionDateOfWeek(
            viewModel
        )
    }
}

///aca puedo enviar el composable por funcion el de withArrow, puedo enviar dos composable
@Composable
fun OptionDateOfWeek(viewModel: AddHabitViewModels) {
    var openOptionDayOfWeek by remember{
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                ///logica
                openOptionDayOfWeek = false
            }
    ) {
        ///circulo base y texto
        CircleWithArrow(viewModel)
        Text(
            text = "Todos los dias"
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
            ///logica
                openOptionDayOfWeek = true
            }
    ) {
        CircleWithArrow(viewModel)
        Text(
            text = "Dia especifico de la semana"
        )
    }

    ///
    if (openOptionDayOfWeek){
        ////logica para empezar
    }
}

@Composable
fun CircleWithArrow(viewModel: AddHabitViewModels) {

    Box(
        modifier = Modifier
            .size(30.dp)
            .padding(end = 2.dp)
            .clickable {

            }
    ) {
        // Círculo base
        Surface(
            modifier = Modifier
                .size(30.dp)
                .graphicsLayer(
                    shape = CircleShape,
                    clip = true,
                ),
            color = Color.DarkGray // Ajusta el color del círculo según tus necesidades
        ) {
            when {

            }
        }

    }
}
