package com.practica.habitos.Data.Models

import androidx.compose.ui.graphics.vector.ImageVector

class Habito(
    val habito: String,
    val descripcion:String,
    val categoria:Categoria,///aca va a ir el nombre y el icono de la categoria
    val diasARealizarloDeLaSemana:List<String>,
    var realizado: Boolean = false,
)