package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.domain.models.DateItem
import com.practica.habitos.ui.theme.RosaditoMasClaro
import com.practica.habitos.ui.theme.onTertiaryLight
import com.practica.habitos.ui.theme.secondaryLight
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarItem(
    dateInRange: State<List<DateItem>>,
    returnTodayDateInRage: ()-> DateItem,
    actualizarHoy: (DateItem) -> Unit
) {
    var backgroundColor by remember { mutableStateOf(onTertiaryLight) }
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = dateInRange.value.indexOf(returnTodayDateInRage()))
    var selectedCardIndex by remember { mutableStateOf(-1) }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically,
        state = scrollState
    ) {
        itemsIndexed(dateInRange.value){index, date->
            val isSelected = index == selectedCardIndex
            Card(
                modifier = Modifier
                    .height(62.dp)
                    .width(50.dp)
                    .padding(1.dp)
                    .clickable {
                        backgroundColor = RosaditoMasClaro
                        selectedCardIndex = index
                        actualizarHoy(date)
                    },
                shape = RoundedCornerShape(20.dp)
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
                            .background(if (isSelected) backgroundColor else MaterialTheme.colorScheme.primaryContainer),
                    ) {
                        Text(
                            text = "${
                                date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("es"))
                                    .toString().subSequence(0, 3)
                            }",
                            color = secondaryLight,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Text(
                            text = "${date.day}",
                                fontSize = 20.sp,
                                color = secondaryLight,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarItemPreview() {

}
