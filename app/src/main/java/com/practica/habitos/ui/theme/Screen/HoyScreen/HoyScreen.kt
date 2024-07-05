package com.practica.habitos.ui.theme.Screen.HoyScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.Data.Models.Habito
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.components.HoyScreenComponent.CalendarItem
import com.practica.habitos.ui.theme.components.HoyScreenComponent.ItemCard
import com.practica.habitos.ui.theme.components.MenuLateral
import com.practica.habitos.ui.theme.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HoyScreenContent(
    navController: NavHostController,
    viewModel: HoyScreenViewModel
){
    //primer error pasarle el viewModel por parametro
    val drawerState : DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    MenuLateral(navController = navController, drawerdState = drawerState, scope = scope ) {
        Scaffold(
            topBar = {
                TopBar(
                    drawerState = drawerState,
                    scope = scope,
                    viewModel.hoy,
                    ){dateItem->
                    viewModel.actualizarHoy(dateItem)
                }
            }
        ) {  paddingValues ->
            Column(modifier = Modifier
                .background(color = BackgroundHoyScree)
                .padding(paddingValues)
            ){
                ///hecho
                CalendarItem(
                    dateInRange = viewModel.dateInRange,
                    returnTodayDateInRage = { viewModel.returnTodayDateInRange() },
                    actualizarHoy =  { dateItem ->  viewModel.actualizarHoy(dateItem)}
                )
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
                LazyColumn(modifier = Modifier
                    .fillMaxHeight()
                    .background(color = BackgroundHoyScree)
                ){
                    items(viewModel.habitos.value.size){ index->
                        viewModel.habitos.value[index].habito.forEach {date->
                            ItemCard(habito = date)
                        }
                    }
                }
            }
        }
    }
}

///esto lo puedo mejorar con un checkbox de material 3
@Composable
fun CircleWithArrowAndCross(
    size: Int,
    habito: Habito
    ) {
    // Usamos un Box para contener el círculo y los iconos
    var completed by remember { mutableStateOf(habito.completed) }
    Box(
        modifier = Modifier
            .size(size.dp)
            .padding(end = 2.dp)
            .clickable {
                completed = !completed
                habito.completed = completed
            }
    ) {
        // Círculo base
        Surface(
            modifier = Modifier
                .size(size.dp)
                .graphicsLayer(
                    shape = CircleShape,
                    clip = true,
                ),
            color = Color.DarkGray // Ajusta el color del círculo según tus necesidades
        ) {
            when {
                completed->TildeCorrecto()
                !completed->XIncorrecto()
            }
        }

    }
}

@Composable
fun XIncorrecto() {
    Icon(
        imageVector = Icons.Default.Clear,
        contentDescription = "no completed",
        tint = Color.Red)
}

@Composable
fun TildeCorrecto() {
   Icon(
       imageVector = Icons.Default.Check,
       contentDescription = "completed",
       tint = Color.Green
   )
}


@Preview(showBackground = true)
@Composable
fun ItemsPreview(){
    val navController = rememberNavController()
    HoyScreenContent(navController = navController, HoyScreenViewModel())
}