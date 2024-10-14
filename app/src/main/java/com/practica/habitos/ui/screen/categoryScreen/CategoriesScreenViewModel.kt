package com.practica.habitos.ui.screen.categoryScreen

import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.practica.habitos.domain.models.Categoria

///todo: inyectar hilt, por ahora lo dejo asi
class CategoriesScreenViewModel(): ViewModel(){
    ///lista de categorias personalizables
    private val _listOfCategoriesCustom = mutableStateOf<List<Categoria>>(emptyList())
    val listOfCategoriesCustom: State<List<Categoria>> = _listOfCategoriesCustom
    ///lista de categorias por defecto
    val categoria  = Categoria(
        nombre = "Deporte",
        icono = androidx.compose.material.icons.Icons.Default.Settings,
        color = Color.Red
    )
    private val _listOfCategoriesDefect = mutableStateOf<List<Categoria>>(emptyList())
    val listOfCategoriesDefect: State<List<Categoria>> = _listOfCategoriesDefect

    ///seteamos desde el inicio los valores de la categoria
    init {
        addCategoriesCustom(categoria)
    }

    ///esta funcion deberia ir a un repositorio y , pero por razones practicos lo dejamos asi por ahora
    private fun addCategoriesCustom(newCategory: Categoria){
        _listOfCategoriesCustom.value += newCategory
    }
}