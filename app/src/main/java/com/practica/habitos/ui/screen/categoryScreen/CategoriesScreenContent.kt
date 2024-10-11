package com.practica.habitos.ui.screen.categoryScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.R
import com.practica.habitos.ui.theme.Rosadito

//todo: antes de crear un nuevo habito debo reaizar la screen de categoias ya que necesito seleccionar una cate
//antes de crear una habito
//todo: crear nueva categoria , eliminar categoria creada , modificar categoria , tener categorias por defecto

@Composable
fun CategoriesScreenContent(
    navController: NavController
){
    ///Column 3 secciones titulo , display, crear categoria 
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Categorias") },
                backgroundColor = MaterialTheme.colorScheme.surface,
                navigationIcon = {
                    //todo: icono de navegacion hacia atras
                    IconButton(onClick = {
                        navController.popBackStack()///vuelve hacia atras
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "volver hacia atras",
                            tint = Rosadito
                        )
                    }
                },
                actions = {
                    //todo: icono de comprar premium
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.habits_24),
                            contentDescription = "comprar premium")
                    }
                    //todo: icono de ayuda
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.help_24),
                            contentDescription = "ayuda")
                    }
                },
            )
        },
    ) {
        paddingValues ->
        Column(modifier = Modifier.padding(paddingValues) ) {

        }
    }
}


@Preview
@Composable
fun CategoriesScreenPreview(){
    val navController = rememberNavController()
    CategoriesScreenContent(navController = navController)
}
