package com.practica.habitos.ui.screen.hoyScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.R
import com.practica.habitos.domain.models.UserHabitLog
import com.practica.habitos.ui.components.hoyScreenComponent.CalendarSection
import com.practica.habitos.ui.components.hoyScreenComponent.CategoryIcon
import com.practica.habitos.ui.components.hoyScreenComponent.DatePickerComponent
import com.practica.habitos.ui.components.hoyScreenComponent.HabitAction
import com.practica.habitos.ui.components.hoyScreenComponent.HabitContent
import com.practica.habitos.ui.components.hoyScreenComponent.ItemCard
import com.practica.habitos.ui.components.hoyScreenComponent.SearchBoxComponent
import com.practica.habitos.ui.components.navigationComponent.CustomTopAppBar
import com.practica.habitos.ui.components.navigationComponent.MenuLateral

// todo:cambiar el filtro en el bscador , añadir funcionalidad de agregar un habito, añadir
//la navegacion del timer
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoyScreenContent(
    navController: NavHostController,
    viewModel: HoyScreenViewModel,
) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val today = viewModel.date
    var openDialogCalendar by remember {
        mutableStateOf(false)
    }
    var openDialogHelp by remember {
        mutableStateOf(false)
    }
    val openSearchDialog  = remember {
        mutableStateOf(false)
    }

    // todo: mejorar el if o else
    MenuLateral(
        navController = navController,
        drawerdState = drawerState,
        scope = scope,
        today = today.value,
    ) {
        Scaffold(
            topBar = {
                if(openSearchDialog.value){
                    //todo:animacion para que se muestre de arriba hacia abajo
                    AnimatedVisibility(
                        visible = openSearchDialog.value,
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight -> -fullHeight }, // Desliza desde arriba
                            animationSpec = tween(durationMillis = 2000) // Duración de la animación
                        ),
                        exit =
                            slideOutVertically(
                                targetOffsetY = { fullHeight -> -fullHeight }, // Desliza hacia arriba
                                animationSpec = tween(durationMillis = 2000), // Duración de la animación
                        )
                    ) {
                        SearchBoxComponent(
                            label = "actividad",
                            modifier = Modifier.height(100.dp),
                            listOfCategory = emptyList(),
                            onFilterForType = {},
                            onFilterByCategory = {},
                            onSaveFilter = {} ,
                            onDeleteFilter = { /*TODO*/ },
                            onBack = { result-> openSearchDialog.value = result},
                        ) {result->
                            //todo: aca debo llamar al viewModel para filtrar
                        }
                    }
                } else {
                    AnimatedVisibility(
                        visible = !openSearchDialog.value,
                        enter =
                            slideInVertically(
                                initialOffsetY = { fullHeight -> -fullHeight }, // Desliza desde arriba
                                animationSpec = tween(durationMillis = 2000), // Duración de la animación
                            ),
                        exit =
                            slideOutVertically(
                                targetOffsetY = { fullHeight -> -fullHeight }, // Desliza hacia arriba
                                animationSpec = tween(durationMillis = 2000), // Duración de la animación
                            ),
                    ) {
                        CustomTopAppBar(
                            drawerState = drawerState,
                            scope = scope,
                            title = if (today.value.isDateActual()) "Hoy" else today.value.convertToString(),
                            actions = {
                                // todo: implementar searchBar en el topAppBar
                                IconButton(onClick = { openSearchDialog.value = true }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.search_24),
                                        contentDescription = "search icon",
                                        modifier = Modifier.size(32.dp),
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        openDialogCalendar = true
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.DateRange,
                                        contentDescription = "calendario",
                                        modifier = Modifier.size(32.dp),
                                    )
                                    if (openDialogCalendar) {
                                        DatePickerComponent(onDissmiss = {
                                            openDialogCalendar = false
                                        }) { dateSelected ->
                                            viewModel.updateDate(dateSelected)
                                        }
                                    }
                                }
                                IconButton(onClick = {
                                    openDialogHelp = true
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.help_24),
                                        contentDescription = null,
                                        modifier = Modifier.size(32.dp),
                                    )
                                    if (openDialogHelp) {
                                        // todo:componentes para la ayuda, dependiendo de la screen llamar a determinado componentes
                                    }
                                }
                            },
                        )
                    }
                }

            },
        ) { paddingValues ->
            Column(
                modifier =
                    Modifier
                        .padding(paddingValues),
            ) {
                CalendarSection(
                    dateInRange = viewModel.dateInRange,
                    returnTodayDateInRage = { viewModel.returnTodayDateInRange() }) { dateItem ->
                    viewModel.updateDate(dateItem)
                }
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
                HabitList(
                    listOfHabits = viewModel.habitos,
                    navController = navController,
                )
            }
        }
    }
}

//todo:aca mejoro el topAppBar
@Composable
fun AppBarAnimation(){

}

///todo: poner en un archivo nuevo
@Composable
fun HabitList(
    listOfHabits: State<List<UserHabitLog>>,
    navController: NavHostController,
) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxHeight(),
    ) {
        items(listOfHabits.value.size) { index ->
            listOfHabits.value[index].habito.forEach { date ->
                ItemCard(
                    icon = {
                        CategoryIcon(
                            icon = date.categoria.icono,
                            contentDescription = date.categoria.nombre,
                            color = date.categoria.color,
                        )
                    },
                    content = {
                        HabitContent(
                            habito = date,
                        )
                    },
                    action = {
                        HabitAction {
                        }
                    },
                ) {
                    //todo: navegar al detalles de la actividad

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemsPreview() {
    val navController = rememberNavController()
    HoyScreenContent(navController = navController, HoyScreenViewModel())
}
