package com.practica.habitos.ui.theme.components.HoyScreenComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.practica.habitos.Data.Models.Habito
import com.practica.habitos.ui.theme.LightGreen
import com.practica.habitos.ui.theme.LightRed

@Composable
fun CircleWithArrowAndCross(
    habito: Habito
) {
    // Usamos un Box para contener el círculo y los iconos
    var completed by remember { mutableStateOf(habito.completed) }
    val clickCount  = remember {
        mutableIntStateOf(0)
    }
    var color by remember {
        mutableStateOf(Color.Gray)
    }
  Box(
        modifier = Modifier
            .size(30.dp)
            .padding(end = 2.dp)
            .clickable {
                ///aca deberia hacer que cada
                clickCount.intValue++
                if(clickCount.intValue>2){
                    clickCount.intValue = 0
                }
                when (clickCount.intValue) {
                   0->{completed = null
                       color = Color.Gray
                   }
                    1 -> {
                        completed = true
                        color = LightGreen
                    }
                    2 -> {
                        completed = false
                        color = LightRed
                    }
                }
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
            color = color, // Ajusta el color del círculo según tus necesidades
        ) {
            when (clickCount.intValue) {
                1->Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "completed",
                    tint = Color.Green,
                )
                2-> Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "no completed",
                    tint = Color.Red,
                )
                0->{color = Color.Transparent}
            }
        }
    }
}