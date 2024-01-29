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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
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
import com.practica.habitos.ui.theme.Rosadito
import com.practica.habitos.ui.theme.components.MenuLateral
import com.practica.habitos.ui.theme.components.TopBar
import kotlinx.coroutines.CoroutineScope
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
            ///hecho
            CalendarItem(viewModel)
            Spacer(modifier = Modifier.padding(bottom = 8.dp))
            //todo:modificaciones a la lista , 1-cuando clickee llevarlo al final , 2- dibujar bien los x y las j
            //
            LazyColumn(modifier = Modifier
                .fillMaxHeight()
                .background(color = BackgroundHoyScree)
            ){
                items(viewModel.habitos.size){ index->
                    viewModel.habitos[index].habito.forEach {date->
                        ItemCard(habito = date)
                    }
                }
            }
        }
    }
}
@Composable
fun CalendarItem(viewModel: HoyScreenViewModel) {

    var backgroundColor by remember {mutableStateOf(Color.DarkGray) }
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = viewModel.dateInRange.indexOf(viewModel.returnTodayDateInRange()))
    var selectedCardIndex by remember { mutableStateOf(-1) }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        state = scrollState
    ) {
        itemsIndexed(viewModel.dateInRange){index, date->
            val isSelected = index == selectedCardIndex
            Card(
                modifier = Modifier
                    .size(58.dp)
                    .height(60.dp)
                    .padding(3.dp)
                    .clickable {
                        backgroundColor = Rosadito
                        selectedCardIndex = index
                        viewModel.actualizarHoy(date)
                    }
                    .background(BackgroundHoyScree)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (isSelected) backgroundColor else Color.DarkGray)
                ) {
                    Text(
                        text = "${date.dayOfWeek.getDisplayName(TextStyle.FULL,Locale("es")).toString().subSequence(0,3)}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${date.day}",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCard(habito: Habito) {
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
                    contentDescription = habito.descripcion,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(16.dp))
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