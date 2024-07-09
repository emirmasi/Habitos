package com.practica.habitos.ui.components.addHabit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.practica.habitos.R
import com.practica.habitos.ui.components.categories.CategoryDisplay
import com.practica.habitos.ui.screen.addHabitScreen.AddHabitViewModels
import com.practica.habitos.ui.theme.BackgroundHoyScree
import com.practica.habitos.ui.theme.Rosadito

@Composable
fun ElegirCategoria(viewModels: AddHabitViewModels) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            .clickable {
                openDialog = true
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(3.dp, color = Rosadito, shape = RoundedCornerShape(16.dp))
                .background(BackgroundHoyScree),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .padding(2.dp),
                ){
                Icon(
                    painter = painterResource(id = R.drawable.icons_categorizar_50),
                    contentDescription = "categorias",
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 3.dp)
                        .background(BackgroundHoyScree),
                    tint = Rosadito
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "categoria",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Rosadito
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = if(viewModels.selectedCategory.value != null) "${viewModels.selectedCategory.value?.nombre}" else "",
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = if(viewModels.selectedCategory.value != null) viewModels.selectedCategory.value!!.color else Color.Black
                )
            }
            Box(
                modifier = Modifier
                    .padding(5.dp),

                ){
                (if(viewModels.selectedCategory.value != null) viewModels.selectedCategory.value?.icono else null)?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "categorias",
                        modifier = Modifier
                            .size(45.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .padding(horizontal = 5.dp)
                            .background(viewModels.selectedCategory.value!!.color),
                        tint = Color.Black
                    )
                }
            }

        }
    }
    if(openDialog){
        Dialog(onDismissRequest = { openDialog = false }) {
            CategoryDisplay(
                categories = viewModels.categories,
                onCategoryClick = {viewModels.setSelectedCategory(it)
                }
            )
        }
    }
}