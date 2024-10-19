package com.practica.habitos.ui.components.habitsScreenComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.domain.models.DIASDELASEMANA
import com.practica.habitos.domain.models.DateItem
import com.practica.habitos.domain.models.getLighColor
import com.practica.habitos.ui.theme.primaryLight
import com.practica.habitos.ui.theme.secondaryLight

@Composable
fun CalendarItemHabitScreen(
    semana: State<List<DateItem>>,
    color: Color,
    diasARealizarloDeLaSemana: Set<DIASDELASEMANA>,
) {
    var backgroundColor by remember { mutableStateOf(color.getLighColor(color)) }
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(start = 14.dp,top = 10.dp,end = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        semana.value.forEach { date ->
            // /realizamos la card
            Card(
                modifier =
                Modifier
                    .height(62.dp)
                    .width(50.dp)
                    .padding(1.dp),
                shape = RoundedCornerShape(20.dp),
            ) {
                Card(
                    modifier =
                    Modifier
                        .fillMaxWidth(),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                        Modifier
                            .fillMaxSize()
                            .height(30.dp)
                            .background(
                                if (diasARealizarloDeLaSemana.contains(date.dayOfWeek)) {
                                    backgroundColor
                                } else {
                                    primaryLight
                                },
                            ),
                    ) {
                        Text(
                            text = "${date.dayOfWeek.toString().subSequence(0, 3)}",
                            color = secondaryLight,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Text(
                            text = "${date.day}",
                            fontSize = 20.sp,
                            color = secondaryLight,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}