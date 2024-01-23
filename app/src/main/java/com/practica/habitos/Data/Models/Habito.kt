package com.practica.habitos.Data.Models

import java.time.LocalDate

class Habito(
    val habito: String,
    val descripcion:String,
    val categoria:Categoria,///aca va a ir el nombre y el icono de la categoria
    val fechaDeInicio:LocalDate,
    val fechaDeFin:LocalDate,
    val diasARealizarloDeLaSemana:Set<DIASDELASEMANA>,
    val cantDeMeses:Int
)