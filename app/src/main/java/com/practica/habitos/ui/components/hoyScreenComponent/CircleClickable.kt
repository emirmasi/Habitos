package com.practica.habitos.ui.components.hoyScreenComponent

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
import com.practica.habitos.ui.theme.LightGreen
import com.practica.habitos.ui.theme.LightRed
import com.practica.habitos.ui.theme.RosaditoMasClaro

// puedo hacerlo para que sea con
@Composable
fun CircleClickable(
    isPaintWithColor: Boolean,
    onSelectedState: (Boolean?)->Unit
) {
    var clickCount by remember {
        mutableIntStateOf(0)
    }
    val isSelected = remember{
        mutableStateOf(false)
    }
    var color by remember {
        mutableStateOf(Color.Gray)
    }
    Box(
        modifier = Modifier
            .size(30.dp)
            .padding(end = 1.dp)
            .graphicsLayer(
                shape = CircleShape,
                clip = true,
            )
            .clickable {
                if(isPaintWithColor){
                    isSelected.value = !isSelected.value
                    color = if(isSelected.value) RosaditoMasClaro else Color.Gray

                }else{
                    clickCount++

                    if(clickCount > 2){
                        clickCount = 0
                    }
                    when (clickCount) {
                        0 -> {
                            onSelectedState(null)
                            color = Color.Gray
                        }
                        1 -> {
                            onSelectedState(true)
                            color = LightGreen
                        }
                        2 -> {
                            onSelectedState(false)
                            color = LightRed
                        }
                    }
                }

            }
    ) {
        // Círculo base
        Surface(
            modifier = Modifier
                .size(30.dp),
            color = color,
        ) {
            if(!isPaintWithColor){
                when (clickCount) {
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
                    0 -> {
                        color = Color.Gray
                    }
                }
            }

        }
    }
}