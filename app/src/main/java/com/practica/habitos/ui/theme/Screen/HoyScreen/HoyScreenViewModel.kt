package com.practica.habitos.ui.theme.Screen.HoyScreen

import androidx.lifecycle.ViewModel
import com.practica.habitos.Data.Models.DateItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HoyScreenViewModel @Inject constructor(

): ViewModel()
{
    val dateInRange = loadDateInRange()

    fun loadDateInRange(): List<DateItem>{
        val inicio = Calendar.getInstance()
        val fin = Calendar.getInstance().apply {
            add(Calendar.MONTH,2)
        }
        return getDayBetween(inicio,fin)
    }
    fun getDayBetween(inicio: Calendar, fin: Calendar): List<DateItem> {

        val calendar = Calendar.getInstance()
        calendar.time = inicio.time

        val dateInRange = mutableListOf<DateItem>()
        while(calendar.before(fin) || calendar.equals(fin)){
            val dateItem = DateItem(
                day = calendar.get(Calendar.DAY_OF_MONTH),
                month = calendar.get(Calendar.MONTH) + 1,  // Adding 1 because months are zero-based
                dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
            )

            dateInRange.add(dateItem)
            calendar.add(Calendar.DATE, 1)
        }
        return dateInRange
    }
}