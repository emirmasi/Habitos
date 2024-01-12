package com.practica.habitos.ui.theme.components

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.IconColor
import com.practica.habitos.ui.theme.Rosadito
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Date

@ExperimentalMaterial3Api
@Composable
fun TopBar(
    drawerState: DrawerState,
    scope:CoroutineScope,
){
    val dayOfMonth:Int
    val month:Int
    val year:Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember{ mutableStateOf("Hoy") }
    val datePickerDialog = DatePickerDialog(
         LocalContext.current,
        {_: DatePicker, year:Int,month:Int,dayofYear:Int->
            date.value = "$dayofYear/$month/$year"
        },year,month,dayOfMonth
    )


    TopAppBar(
        title = {
            Text(
                text = date.value,
                color = com.practica.habitos.ui.theme.Text
            )
                },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }
            ) {
                Icon(
                    Icons.Outlined.Menu,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp),
                    tint = Rosadito
                )
            }
        },
        actions = {
            //aca va el calendario
            IconButton(
                onClick = {
                    datePickerDialog.show()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = "calendario",
                    modifier = Modifier.size(40.dp),
                    tint = IconColor
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = BackgroundHoyScree
        )
    )
}