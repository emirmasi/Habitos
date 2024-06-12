package com.practica.habitos.ui.theme.components.AddHabit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.ui.theme.Rosadito
import com.practica.habitos.ui.theme.Screen.AddHabitScreen.AddHabitViewModels
import com.practica.habitos.ui.theme.Screen.HoyScreen.TildeCorrecto

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

    }
}

///aca puedo enviar el composable por funcion el de withArrow, puedo enviar dos composable
@Composable
fun OptionDateOfWeek(
    viewModel: AddHabitViewModels,
    circleWitArrow: @Composable (AddHabitViewModels, MutableState<Boolean>)->Unit,
    selectedDayOfWeek: @Composable ()->Unit,
    textForTitle: String,
    desplegable:Boolean,
) {
    var openOptionDayOfWeek by remember{
        mutableStateOf(false)
    }
    var clickOption = remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                ///logica
                clickOption.value = !clickOption.value
                if (desplegable)
                    openOptionDayOfWeek = true
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        circleWitArrow(viewModel,clickOption)
        Text(
            text = textForTitle,
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun CircleWithArrow(
    viewModel: AddHabitViewModels,
    clickOption: MutableState<Boolean>
) {

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
            when (clickOption.value) {
                true -> TildeCorrecto()
                else -> {}
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OptionDateOfWeekPreview(){
    OptionDateOfWeek(
        viewModel = AddHabitViewModels(),
        circleWitArrow = {view,state-> CircleWithArrow(view,state) },
        selectedDayOfWeek = { /*TODO*/ },
        textForTitle = "Toda la semana",
        desplegable = false
    )
}