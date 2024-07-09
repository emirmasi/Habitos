package com.practica.habitos.ui.screen.addHabitScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.ui.theme.BackgrounBottomBar
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.components.addHabit.DescripcionHabito
import com.practica.habitos.ui.components.addHabit.ElegirCategoria
import com.practica.habitos.ui.components.addHabit.FechaDeFin
import com.practica.habitos.ui.components.addHabit.FechaDeInico
import com.practica.habitos.ui.components.addHabit.NameHabit

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddHabit(
    navHostController: NavHostController,
    viewModels: AddHabitViewModels,
)  {
    Scaffold(
        topBar = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "atras",
                modifier =
                Modifier
                    .clickable {
                        navHostController.navigateUp()
                    }
                    .size(50.dp)
                    .background(BackgrounBottomBar),
            )
        },
        containerColor = BackgrounBottomBar,
    ) {
            paddingValues ->
        Column(
            modifier =
            Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .background(BackgroundHoyScree),
        ) {
            // /nombre del habito
            NameHabit(viewModels)
            // /descripcion del habito
            DescripcionHabito(viewModels)
            // /categoria
            ElegirCategoria(viewModels)
            // /fecha de inicio
            FechaDeInico(viewModels)
            // /fecha de fin
            FechaDeFin(viewModels)
            // /dias a realizar a la semana
        }
    }
}
@Preview(showBackground = true)
@Composable
fun NameHabitPreview()  {
    val navController = rememberNavController()
    AddHabit(navHostController = navController, AddHabitViewModels())
}
