package com.practica.habitos.ui.theme.Screen.HoyScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.Data.Models.Categoria
import com.practica.habitos.Data.Models.Habito
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.IconCategories


///aca va a estar la lista de habitos y para agregar
@Composable
fun HoyScreenContent(
    navController: NavHostController
){
    ///va a ir una lista y un boton flotante de agregar
    ////me traigo la lista de habitos que tengo desde el vm
    val habitos = listOf(
        Habito("habito1","correr diario", Categoria("deporte", Icons.Default.Star,Color.Magenta), listOf("martes","miercoles")),
        Habito("habito2","comer", Categoria("comida", Icons.Default.Star,Color.Green), listOf("martes","miercoles"))
    )

    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .background(color = BackgroundHoyScree)
    ){
        items(habitos.size){ index->
            ItemCard(habitos[index])
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
                habito.realizado = !habito.realizado
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
                        .padding(horizontal = 3.dp )
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
        }

    }
}



@Preview(showBackground = true)
@Composable
fun ItemsPreview(){
    val navController = rememberNavController()
    HoyScreenContent(navController = navController)
}