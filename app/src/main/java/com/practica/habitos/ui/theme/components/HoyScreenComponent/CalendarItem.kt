package com.practica.habitos.ui.theme.components.HoyScreenComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.Data.Models.DateItem
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.Rosadito
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarItem(
    dateInRange: State<List<DateItem>>,
    returnTodayDateInRage: ()-> DateItem,
    actualizarHoy: (DateItem) -> Unit
) {

    var backgroundColor by remember { mutableStateOf(Color.DarkGray) }
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = dateInRange.value.indexOf(returnTodayDateInRage()))
    var selectedCardIndex by remember { mutableStateOf(-1) }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        state = scrollState
    ) {
        itemsIndexed(dateInRange.value){index, date->
            val isSelected = index == selectedCardIndex
            Card(
                modifier = Modifier
                    .size(58.dp)
                    .height(60.dp)
                    .padding(3.dp)
                    .clickable {
                        backgroundColor = Rosadito
                        selectedCardIndex = index
                        actualizarHoy(date)
                    }
                    .background(BackgroundHoyScree)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (isSelected) backgroundColor else Color.DarkGray)
                ) {
                    Text(
                        text = "${date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("es")).toString().subSequence(0,3)}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${date.day}",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}