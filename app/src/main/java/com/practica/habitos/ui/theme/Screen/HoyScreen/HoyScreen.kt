package com.practica.habitos.ui.theme.Screen.HoyScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.Data.Models.Habito
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.IconCategories
import com.practica.habitos.ui.theme.components.MenuLateral
import com.practica.habitos.ui.theme.components.TopBar
import kotlinx.coroutines.CoroutineScope
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoyScreenContent(
    navController: NavHostController,
    viewModel: HoyScreenViewModel
){

    val drawerState : DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    MenuLateral(navController = navController, drawerdState = drawerState, scope = scope ) {
        ContenidoHoyScreen(drawerState,scope,viewModel)
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoHoyScreen(
    drawerState: DrawerState,
    scope:CoroutineScope,
    viewModel: HoyScreenViewModel
){

    Scaffold(
        topBar = {TopBar(drawerState = drawerState,scope = scope,viewModel = viewModel)}
    ) {  paddingValues ->
        Column(modifier = Modifier
            .background(color = BackgroundHoyScree)
            .padding(paddingValues)
        ){

            ///aca va el calendario
            CalendarItem(viewModel)

            viewModel.hoy.value?.toConvert()?.let { Text(text = it) }

            Spacer(modifier = Modifier.padding(bottom = 8.dp))
            LazyColumn(modifier = Modifier
                .fillMaxHeight()
                .background(color = BackgroundHoyScree)
            ){
                items(viewModel.habitos.size){ index->
                    ItemCard(viewModel.habitos[index])
                }
            }
        }
    }
}
@Composable
fun CalendarItem(viewModel: HoyScreenViewModel) {

    Row {
        viewModel.dateInRange.forEach { date->
            Card(
                modifier = Modifier
                    .size(58.dp)
                    .background(BackgroundHoyScree)
                    .padding(3.dp)
                    .clickable {
                        viewModel.actualizarHoy(date)
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray)
                ) {
                    Text(
                        text = "${date.dayOfWeek.getDisplayName(TextStyle.FULL,Locale("es")).toString().subSequence(0,3)}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${date.day}",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCard(habito : Habito) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)
            .clickable {

            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(BackgroundHoyScree),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment =  Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
                ){
                Icon(
                    imageVector = habito.categoria.icono,
                    contentDescription = habito.habito,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .padding(horizontal = 3.dp)
                        .background(habito.categoria.color),
                    tint = IconCategories
                    )
            }
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = habito.habito,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = com.practica.habitos.ui.theme.Text
                )
            }
            Box(
                modifier = Modifier
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ){
                CircleWithArrowAndCross(size = 30,habito)
                ///aca hay que llevar el habito devuelta para ver si se modifico
            }

        }
    }
}
@Composable
fun CircleWithArrowAndCross(
    size: Int,
    habito: Habito
    ) {
    // Usamos un Box para contener el círculo y los iconos
    Box(
        modifier = Modifier
            .size(size.dp)
            .padding(end = 2.dp)
    ) {
        // Círculo base
        Surface(
            modifier = Modifier
                .size(size.dp)
                .graphicsLayer(
                    shape = CircleShape,
                    clip = true,
                ),
            color = Color.DarkGray, // Ajusta el color del círculo según tus necesidades
        ) {
            /*
            if(habito.realizado){
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "chequeado",
                    tint = Color.Green)
            }else{
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "no lo realizon",
                    tint = Color.Red)
            }

             */
        }

    }
}



@Preview(showBackground = true)
@Composable
fun ItemsPreview(){
    val navController = rememberNavController()
    HoyScreenContent(navController = navController, HoyScreenViewModel())
}