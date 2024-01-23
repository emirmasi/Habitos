package com.practica.habitos.ui.theme.Screen.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.Data.Models.Screens
import com.practica.habitos.Domain.utils.currentRoute
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.IconColor
import com.practica.habitos.ui.theme.Rosadito
import com.practica.habitos.ui.theme.components.BottomBar
import com.practica.habitos.ui.theme.components.MenuLateral
import com.practica.habitos.ui.theme.components.NavigationComponent
import com.practica.habitos.ui.theme.components.TopBar
import kotlinx.coroutines.CoroutineScope

///aca hacemos el las navegaciones el top

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val controller = rememberNavController()
    val drawerState : DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    MenuLateral(navController = controller, drawerdState = drawerState, scope = scope) {
        Contenido(navController = controller, drawerState = drawerState, scope = scope)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contenido(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
){
    Scaffold(
        topBar = {
            if(currentRoute(navController = navController) == Screens.Hoy.route){
                TopBar(drawerState, scope = scope)
            }
                 },
        bottomBar = { BottomBar(navController = navController)},
        floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier
                        .background(BackgroundHoyScree),
                    shape = MaterialTheme.shapes.medium,
                    onClick = {

                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(60.dp)
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