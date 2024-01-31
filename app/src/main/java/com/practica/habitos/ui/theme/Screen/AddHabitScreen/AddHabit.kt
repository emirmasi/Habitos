package com.practica.habitos.ui.theme.Screen.AddHabitScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.habitos.Data.Models.Categoria
import com.practica.habitos.Data.Models.DateItem
import com.practica.habitos.ui.theme.BackgrounBottomBar
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.IconCategories
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddHabit(
    navHostController: NavHostController,
    viewModels: AddHabitViewModels,
)  {
    Scaffold(
        topBar = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "atras",
                modifier =
                Modifier
                    .clickable {
                        navHostController.navigateUp()
                    }
                    .size(50.dp)
                    .background(BackgrounBottomBar),
            )
        },
        containerColor = BackgrounBottomBar,
    ) {
            paddingValues ->

        Column(
            modifier =
            Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .background(BackgroundHoyScree),
        ) {
            // /nombre del habito
            NameHabit(viewModels)
            // /descripcion del habito
            DescripcionHabito(viewModels)
            // /categoria
            ElegirCategoria(viewModels)
            // /fecha de inicio
            FechaDeInico(viewModels)
            // /fecha de fin
            FechaDeFin(viewModels)
            // /dias a realizar a la semana
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FechaDeFin(viewModels: AddHabitViewModels) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FechaDeInico(viewModels: AddHabitViewModels) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    val state = rememberDatePickerState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp,vertical = 10.dp)
    ) {

        ///aca el icono del date y el clickeable
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "elegir fecha de inicio",
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    openDialog = true
                }
                .align(Alignment.CenterVertically),
            tint = Color.Gray
        )
        if(openDialog){
            DatePickerDialog(
                onDismissRequest = { openDialog = false },
                confirmButton = {
                    Button(onClick = { openDialog = false }) {
                        Text(text = "Confirmar")
                        val date = state.selectedDateMillis
                        date?.let {
                            val instance: LocalDate = Instant.ofEpochMilli(date).atZone(
                                ZoneId.of("UTC")).toLocalDate()
                            viewModels.setSelectedFechaDeInicio(
                                DateItem(
                                    instance.dayOfMonth,
                                    instance.month.value,
                                    instance.year,
                                    instance.dayOfWeek
                                )
                            )
                        }
                    }
                }
            ) {
                DatePicker(state = state)
            }
        }
    }

}



@Composable
fun ElegirCategoria(viewModels: AddHabitViewModels) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp),
    ) {
        Button(
            onClick = {
                // /aca hay que abrir un dialog
                openDialog = !openDialog
            },
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
        ) {
            Text(text = "Elegir categoria")
            if (openDialog)
                {
                    Dialog(onDismissRequest = { openDialog = false }) {
                        CategoryDisplay(
                            categories = viewModels.categories,
                            onCategoryClick = { viewModels.setSelectedCategory(it) },
                        )
                    }
                }
        }
        if (viewModels.selectedCategory.value != null)
            {
                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(BackgroundHoyScree),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier =
                            Modifier
                                .padding(2.dp),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        Icon(
                            imageVector = viewModels.selectedCategory.value!!.icono,
                            contentDescription = "",
                            modifier =
                            Modifier
                                .size(45.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .padding(horizontal = 3.dp)
                                .background(viewModels.selectedCategory.value!!.color),
                            tint = IconCategories,
                        )
                    }
                    Box(
                        modifier =
                            Modifier
                                .weight(1f),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        Text(
                            text = viewModels.selectedCategory.value!!.nombre,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = com.practica.habitos.ui.theme.Text,
                        )
                    }
                }
            }
    }
}

@Composable
fun CategoryDisplay(
    categories: List<Categoria>,
    onCategoryClick: (Categoria) -> Unit,
) {
    var selectedCategoria by remember { mutableStateOf<Categoria?>(null) }
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier =
            Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            items(categories) {
                    cat ->

                Column(
                    modifier =
                    Modifier
                        .clickable {
                            selectedCategoria = cat
                            onCategoryClick(cat)
                        }
                        .padding(10.dp),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .padding(2.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = cat.icono,
                            contentDescription = cat.nombre,
                            modifier =
                            Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(cat.color),
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = cat.nombre,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = Color.Green,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescripcionHabito(viewModels:AddHabitViewModels) {
    TextField(
        value = viewModels.descripcion.value,
        onValueChange = { newDescripcion ->
            if (newDescripcion.count { it == '\n' } < 3)
                {
                    viewModels.setDescription(newDescripcion)
                }
        },
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp),
        textStyle =
            TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            ),
        placeholder = {
            Text(
                text = "descripcion",
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors =
            TextFieldDefaults.textFieldColors(
                containerColor = Color.DarkGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        maxLines = 3,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameHabit(viewModels: AddHabitViewModels) {
    TextField(
        value = viewModels.selectedNameHabit.value,
        onValueChange = { newHabit ->
            viewModels.setNameHabit(newHabit)
        },
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp),
        textStyle =
            TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            ),
        placeholder = {
            Text(
                text = "nombre del habito",
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors =
            TextFieldDefaults.textFieldColors(
                containerColor = Color.DarkGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
    )
}

@Preview(showBackground = true)
@Composable
fun NameHabitPreview()  {
    val navController = rememberNavController()
    AddHabit(navHostController = navController, AddHabitViewModels())
}
