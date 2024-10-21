package com.practica.habitos.ui.screen.timerScreen

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import java.util.concurrent.TimeUnit

class TimerScreenViewModel: ViewModel() {

    ///logica para temporizador
    private var countDownTimer: CountDownTimer? = null

    private val userInputHour: Long = TimeUnit.HOURS.toMillis(1)
    private val userInputMinute = TimeUnit.HOURS.toMillis(10)
    private val userInputSecond = TimeUnit.HOURS.toMillis(30)

}