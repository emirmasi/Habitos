package com.practica.habitos.ui.screen.categoryScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.R
import com.practica.habitos.ui.components.categories.CategoriesCustom
import com.practica.habitos.ui.components.categories.CategoriesForDefect
import com.practica.habitos.ui.theme.Rosadito

//todo: antes de crear un nuevo habito debo reaizar la screen de categoias ya que necesito seleccionar una cate
//antes de crear una habito
//todo: crear nueva categoria , eliminar categoria creada , modificar categoria , tener categorias por defecto

@Composable
fun CategoriesScreenContent(
    navController: NavController,
    categoiresScreenViewModel: CategoriesScreenViewModel = CategoriesScreenViewModel(),
)  {
    ///Column 3 secciones titulo , display, crear categoria 
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Categorias",
                        fontWeight = FontWeight.SemiBold,
                    )
                },
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
        val isPremium =
            false // /esta variable deberia estar en el viewModel , ya que nos indica si el usuario tiene premium

        // /luego fijarme el tema de state
        val listOfCategoriesForDefect = categoiresScreenViewModel.listOfCategoriesDefect

        val listOfCategoriesCustom = categoiresScreenViewModel.listOfCategoriesCustom

        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
        ) {
            // /todo: componente de crear nuevas categorias
            CategoriesCustom(listOfCategories = listOfCategoriesCustom.value, isPremium = isPremium)
            // /todo:componente para ver las categorias por defecto
            Spacer(modifier = Modifier.padding(top = 30.dp, bottom = 30.dp))

            CategoriesForDefect(
                listOfCategoriesForDefect = listOfCategoriesForDefect.value,
                isPremium,
            ) { catEditable ->
                // /todo:metodo para editar una categoria
            }

            Spacer(modifier = Modifier.weight(1f))

            FilledTonalButton(
                onClick = { /*TODO*/ },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                colors =
                    androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Rosadito,
                    ),
            ) {
                Text(
                    text = "Nueva categoria",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }
        }
    }
}
// /todo: si son editable las categorias por defecto deberia traerlo de una bd, si no es premium deberia guardarlo en el dispositivo ,
// todo: pero si es editable entonces tengo que hacer el crud de las categorias , en definitiva deberia poder tener dos db

@Preview
@Composable
fun CategoriesScreenPreview()  {
    val navController = rememberNavController()
    CategoriesScreenContent(navController = navController, CategoriesScreenViewModel())
}
