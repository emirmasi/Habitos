package com.practica.habitos.ui.components.habitsScreenComponent

import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.practica.habitos.R
import com.practica.habitos.domain.models.Categoria
import com.practica.habitos.ui.components.hoyScreenComponent.SearchBoxComponent
import com.practica.habitos.ui.components.navigationComponent.CustomTopAppBar
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHabitScreen(
    openDialogSearch: MutableState<Boolean>,
    openFiltersIcon: MutableState<Boolean>,
    title: String,
    drawerState: DrawerState,
    scope: CoroutineScope,
    listOfCategory: List<Categoria>,
    onFilterForCategory: (String) -> Unit,
    onSearch: (String)->Unit,
){
    if(openDialogSearch.value){
        SearchBoxComponent(
            label = "Buscar",
            listOfCategory = listOfCategory,
            onFilterByCategory = {filter-> onFilterForCategory(filter)},
            onSaveFilter = {},
            onDeleteFilter = { /*TODO*/ },
            onBack = {result->openDialogSearch.value = result},
            onSearch = {search->onSearch(search)},
        ) {
            //en este caso no podemos un filtro por tipo
        }
    }else {
        CustomTopAppBar(
            drawerState = drawerState,
            scope = scope,
            title = title,
            actions = {
                IconButton(onClick = { openDialogSearch.value = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_24),
                        contentDescription = "search icon",
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter_list_24),
                        contentDescription = "filter icon",
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.save_habits_24),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            },
        )
    }
}