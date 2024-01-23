package com.practica.habitos.Data.Models

import java.time.LocalDate

class UserHabitLog(
    val habito:Habito,
    val fecha: LocalDate,
    var completed: Boolean
)

