package com.practica.habitos.ui.theme.Screen.AddHabitScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.practica.habitos.Data.Models.Categoria

///todo : inyectar hilt
class AddHabitViewModels: ViewModel(){

    val categories = listOf<Categoria>(
        Categoria("Deporte", Icons.Default.Person, Color.Green),
        Categoria("mal", Icons.Default.Check, Color.Blue),
        Categoria("feo", Icons.Default.List, Color.Red),
        Categoria("gordo", Icons.Default.AddCircle, Color.White),
        Categoria("puto", Icons.Default.Favorite, Color.Yellow),
        Categoria("etc", Icons.Default.Clear, Color.Cyan),
        Categoria("milo", Icons.Default.DateRange, Color.LightGray),
        Categoria("colo", Icons.Default.Create, Color.Cyan)
    )
    private val _selectedCategory = mutableStateOf<Categoria?>(null)
    val selectedCategory: State<Categoria?> = _selectedCategory

    fun setSelectedCategory(categorySelected: Categoria){
        _selectedCategory.value = categorySelected
    }

}