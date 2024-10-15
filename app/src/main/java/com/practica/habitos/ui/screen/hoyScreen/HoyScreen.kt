package com.practica.habitos.ui.screen.hoyScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.practica.habitos.ui.components.hoyScreenComponent.CalendarSection
import com.practica.habitos.ui.components.hoyScreenComponent.HabitList
import com.practica.habitos.ui.components.hoyScreenComponent.TopBar
import com.practica.habitos.ui.components.navigationComponent.MenuLateral
import com.practica.habitos.ui.theme.IconColor
import com.practica.habitos.ui.theme.Rosadito

///todo: hacer las screen para crear un nuevo habito
@Composable
fun HoyScreenContent(
    navController: NavHostController,
    viewModel: HoyScreenViewModel,
) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val today = viewModel.date
    val openDialogCalendar = remember { mutableStateOf(false) }
    val openDialogHelp = remember { mutableStateOf(false) }
    val openSearchDialog  = remember { mutableStateOf(false) }
    val listOfCategories = viewModel.listOfCategories
    MenuLateral(
        navController = navController,
        drawerdState = drawerState,
        scope = scope,
        today = today.value,
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    openSearchDialog = openSearchDialog,
                    openDialogCalendar = openDialogCalendar,
                    openDialogHelp = openDialogHelp,
                    today = today,
                    drawerState = drawerState,
                    scope = scope,
                    listOfCategory = listOfCategories.value,
                    onDateSelected = { date -> viewModel.updateDate(date) },
                    onFilterForType = { filter -> viewModel.filterForType(filter) },
                    onFilterForCategory = { filter -> viewModel.filterForCategory(filter) },
                    onSearch = { search -> viewModel.searchHabits(search) }
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
            }
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

@Preview(showBackground = true)
@Composable
fun ItemsPreview() {
    val navController = rememberNavController()
    HoyScreenContent(navController = navController, HoyScreenViewModel())
}
