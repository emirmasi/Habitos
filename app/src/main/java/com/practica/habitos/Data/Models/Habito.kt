package com.practica.habitos.Data.Models

import com.practica.habitos.Domain.Models.Categoria
import com.practica.habitos.Domain.Models.DIASDELASEMANA
import com.practica.habitos.Domain.Models.DateItem

class Habito(
    val habito: String,
    val descripcion:String,
    val categoria: Categoria,///aca va a ir el nombre y el icono de la categoria
    val fechaDeInicio: DateItem,
    val fechaDeFin:DateItem,
    val diasARealizarloDeLaSemana:Set<DIASDELASEMANA>,///esto deberia ser un DayOfWeek
    var completed :Boolean
)