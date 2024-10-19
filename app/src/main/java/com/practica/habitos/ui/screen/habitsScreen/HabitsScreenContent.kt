package com.practica.habitos.ui.screen.habitsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.ui.components.habitsScreenComponent.TopBarHabitScreen
import com.practica.habitos.ui.components.navigationComponent.MenuLateral
import com.practica.habitos.ui.theme.IconColor
import com.practica.habitos.ui.theme.Rosadito

// todo:creamos la ui de la pantalla de habitos
@Composable
fun HabitsScreenContent(
    navHostController: NavHostController,
    viewModel: HabitsScreenViewModel = HabitsScreenViewModel(),
) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val today = viewModel.date

    val openSearchDialog = remember {
        mutableStateOf(false)
    }

    MenuLateral(
        navController = navHostController,
        drawerdState = drawerState,
        scope = scope,
        today = today.value,
    ) {
        Scaffold(
            topBar = {
                // /todo: hacer una topAppBar nuevo o utilizar el que ya tengo ,
                TopBarHabitScreen(
                    openDialogSearch = openSearchDialog,
                    openFiltersIcon = openSearchDialog,
                    title = "Habitos",
                    drawerState = drawerState,
                    scope = scope,
                    listOfCategory = emptyList(),
                    onFilterForCategory = {},
                    onSearch = {},
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    modifier =
                    Modifier
                        .size(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    onClick = {
                        ///todo:aca agregamos un nuevo habito
                    },
                    containerColor = Rosadito,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        modifier =
                        Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Rosadito),
                        tint = IconColor,
                    )
                }
            },
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                val semana = viewModel.semana

            }
        }
    }
}


@Preview
@Composable
fun HabitsScreenPreview() {
    HabitsScreenContent(navHostController = rememberNavController())
}