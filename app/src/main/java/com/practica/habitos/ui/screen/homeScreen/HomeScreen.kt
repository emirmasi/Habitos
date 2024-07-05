package com.practica.habitos.ui.screen.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.ui.screen.components.navigationComponent.BottomBar
import com.practica.habitos.ui.screen.components.navigationComponent.NavigationComponent
import com.practica.habitos.ui.theme.IconColor
import com.practica.habitos.ui.theme.Rosadito
///aca hacemos el las navegaciones el top

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(){
    val controller = rememberNavController()
    Contenido(navController = controller)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Contenido(
    navController: NavHostController,
){
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier
                        .size(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    onClick = {
                            ///aca agregamos un nuevo habito

                    },
                    containerColor = Rosadito
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Rosadito),
                        tint = IconColor
                    )
                }
        }
    ){paddingValues ->
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            NavigationComponent(navHostController = navController )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    Surface {
        HomeScreen()
    }
}