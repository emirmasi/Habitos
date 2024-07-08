package com.practica.habitos.domain.models

class Habito(
    val habito: String,
    val descripcion: String,
    val categoria: Categoria,
    val fechaDeInicio: DateItem,
    val fechaDeFin: DateItem,
    val diasARealizarloDeLaSemana: Set<DIASDELASEMANA>,
    var completed: Boolean? = null,
)
