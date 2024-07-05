package com.practica.habitos.ui.components.navigationComponent

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica.habitos.Domain.Models.DateItem
import com.practica.habitos.R
import com.practica.habitos.ui.components.HoyScreenComponent.DatePickerComponent
import com.practica.habitos.ui.theme.Rosadito
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun AppBar(
    drawerState: DrawerState,
    scope: CoroutineScope,
    hoy: State<DateItem>,
    actualizarHoy: (DateItem) -> Unit,
) {
    
    var openDialogCalendar by remember {
        mutableStateOf(false)
    }
    
    var openDialogHelp by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Text(
                text = if(hoy.value.isDateActual()) "Hoy" else hoy.value.convertToString(),
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_drawer),
                    contentDescription = null,
                    modifier =
                        Modifier
                            .size(42.dp),
                    tint = Rosadito,
                )
            }
        },
        actions = {
            // aca va el calendario
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
                    DatePickerComponent(onDissmiss = { openDialogCalendar = false }) {
                        dateSelected->
                        actualizarHoy(dateSelected)
                    }
                }
            }
            IconButton(onClick = {
                openDialogHelp = true
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.help_24),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                if(openDialogHelp){
                    ///componentes para la ayuda
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

}
