package com.practica.habitos.ui.screen.components.AddHabit

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.Rosadito
import com.practica.habitos.ui.screen.addHabitScreen.AddHabitViewModels

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameHabit(viewModels: AddHabitViewModels) {

    OutlinedTextField(
        value = viewModels.selectedNameHabit.value,
        onValueChange = { newHabit ->
            viewModels.setNameHabit(newHabit)
        },
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            .border(3.dp, color = Rosadito, shape = RoundedCornerShape(16.dp)),
        textStyle =
        TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Rosadito,
        ),
        placeholder = {
            Text(
                text = "nombre del habito",
                fontWeight = FontWeight.Bold,
                color = Rosadito,
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors =
        TextFieldDefaults.textFieldColors(
            containerColor = BackgroundHoyScree,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}