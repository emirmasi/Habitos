package com.practica.habitos.domain.models

import java.time.DayOfWeek

fun DayOfWeek.convertToDIADELASEMANA(dayOfWeek: DayOfWeek):DIASDELASEMANA{
    val dia = mapOf(
        1 to DIASDELASEMANA.LUNES,
        2 to DIASDELASEMANA.MARTES,
        3 to DIASDELASEMANA.MIERCOLES,
        4 to DIASDELASEMANA.JUEVES,
        5 to DIASDELASEMANA.VIERNES
    )
    return dia.getValue(dayOfWeek.value)
}