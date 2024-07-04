package com.practica.habitos.Domain.Models

///podria hacer que completed pueda ser null
class Habito(
    val habito: String,
    val descripcion:String,
    val categoria: Categoria,///aca va a ir el nombre y el icono de la categoria
    val fechaDeInicio: DateItem,
    val fechaDeFin: DateItem,
    val diasARealizarloDeLaSemana:Set<DIASDELASEMANA>,///esto deberia ser un DayOfWeek
    var completed :Boolean? = null
)
