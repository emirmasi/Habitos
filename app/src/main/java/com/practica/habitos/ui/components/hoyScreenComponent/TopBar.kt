package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.practica.habitos.R
import com.practica.habitos.domain.models.Categoria
import com.practica.habitos.domain.models.DateItem
import com.practica.habitos.ui.components.navigationComponent.CustomTopAppBar
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    openSearchDialog: MutableState<Boolean>,
    openDialogCalendar: MutableState<Boolean>,
    openDialogHelp: MutableState<Boolean>,
    today: State<DateItem>,
    drawerState: DrawerState,
    scope: CoroutineScope,
    listOfCategory: List<Categoria>,
    onDateSelected: (DateItem) -> Unit,
    onFilterForType: (String) -> Unit,
    onFilterForCategory: (String) -> Unit,
) {
    if (openSearchDialog.value) {
        SearchBoxComponent(
            label = "actividad",
            modifier = Modifier.height(100.dp),
            listOfCategory = emptyList(),
            onFilterForType = {filter-> onFilterForType(filter)},
            onFilterByCategory = {filter-> onFilterForCategory(filter)},
            onSaveFilter = {},
            onDeleteFilter = {},
            onBack = { result -> openSearchDialog.value = result },
            onSearch = { result -> /* Lógica de filtro */ }
        )
    } else {
        AnimatedVisibility(
            visible = !openSearchDialog.value,
            enter = slideInVertically(initialOffsetY = { -it }, animationSpec = tween(2000)),
            exit = slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(2000))
        ) {
            CustomTopAppBar(
                drawerState = drawerState,
                scope = scope,
                title = if (today.value.isDateActual()) "Hoy" else today.value.convertToString(),
                actions = {
                    IconButton(onClick = { openSearchDialog.value = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_24),
                            contentDescription = "search icon",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    IconButton(onClick = { openDialogCalendar.value = true }) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "calendario",
                            modifier = Modifier.size(32.dp)
                        )
                        if (openDialogCalendar.value) {
                            DatePickerComponent(
                                onDissmiss = { openDialogCalendar.value = false },
                                onDateSelected = { dateSelected -> onDateSelected(dateSelected) }
                            )
                        }
                    }
                    IconButton(onClick = { openDialogHelp.value = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.help_24),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                        if (openDialogHelp.value) {
                            // Lógica para ayuda
                        }
                    }
                }
            )
        }
    }
}
