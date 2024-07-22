package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.practica.habitos.ui.theme.IconCategories

@Composable
fun CategoryIcon(
    icon: ImageVector,
    contentDescription: String,
    color: Color,
){
    Box(
        modifier = Modifier
            .padding(2.dp),
        contentAlignment = Alignment.CenterStart
    ){
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .padding(horizontal = 3.dp, vertical = 3.dp)
                .background(color),
            tint = IconCategories
        )
    }
}