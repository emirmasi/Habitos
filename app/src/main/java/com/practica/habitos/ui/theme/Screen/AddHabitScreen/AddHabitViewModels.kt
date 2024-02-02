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
import com.practica.habitos.Data.Models.DateItem
import java.time.LocalDate

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
    private val _nameHabit =  mutableStateOf<String>("")
    val selectedNameHabit:State<String> = _nameHabit

    private val _descripcion = mutableStateOf<String>("")
    val descripcion: State<String> = _descripcion

    private val _selectedCategory = mutableStateOf<Categoria?>(null)
    val selectedCategory: State<Categoria?> = _selectedCategory

    private val _selectedFechaDeInicio = mutableStateOf<DateItem?>(null)
    val selectedFechaDeInicio: State<DateItem?> = _selectedFechaDeInicio

    private  val _selectedFechaDeFin = mutableStateOf<DateItem?>(null)
    val selectedFechaDeFin: State<DateItem?> = _selectedFechaDeFin

    fun setDescription(descripcion: String){
        _descripcion.value = descripcion
    }
    fun setNameHabit(newNameHabit:String){
        _nameHabit.value = newNameHabit
    }
    fun setSelectedCategory(categorySelected: Categoria){
        _selectedCategory.value = categorySelected
    }

    fun setSelectedFechaDeInicio(fechaDeInicio: DateItem?){
        _selectedFechaDeInicio.value = fechaDeInicio
    }
    fun setSelectedFechaDeFin(fechaDeFin:DateItem){
        _selectedFechaDeFin.value = fechaDeFin
    }

    fun fechaDeFinEsMayorQueFechaDeInicio(fechaDeFin: DateItem):Boolean{
        when{
            fechaDeFin.year > selectedFechaDeInicio.value?.year!! -> true
            fechaDeFin.year < selectedFechaDeInicio.value?.year!! -> false
            fechaDeFin.month > selectedFechaDeInicio.value?.month!! -> true
            fechaDeFin.month < selectedFechaDeInicio.value?.month!! -> false
            fechaDeFin.day > selectedFechaDeInicio.value?.day!! -> true
            else->false
        }
        return false
    }

    fun parseDateStringToDateItem(date: String): DateItem? {
        val part = date.split("/")
        if(part.size == 3){
            val day = part[0].toIntOrNull()?:0
            val month = part[1].toIntOrNull()?:0
            val year = part[3].toIntOrNull()?:0
            val dayOfWeek = LocalDate.of(year,month,day).dayOfWeek
            return DateItem(day,month,year, dayOfWeek = dayOfWeek)
        }else
            return null
    }

}