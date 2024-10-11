package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterForType(
    listOfType: List<String>,
    onFilterSelected: (String)->Unit,
){
    val isExpanded = remember{
        mutableStateOf(false)
    }
    val filterSelected = remember {
        mutableStateOf("Todo")
    }
    ExposedDropdownMenuBox(
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = !isExpanded.value },
        modifier = Modifier
            .width(130.dp)
    ) {
        //ancho 130
        TextField(
            value = filterSelected.value,
            onValueChange ={},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
            },
            modifier = Modifier
                .menuAnchor(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            )
        )
        ExposedDropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }) {
            listOfType.forEach { item->
                DropdownMenuItem(
                    text = { Text(
                        text = item,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    ) },
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        filterSelected.value = item
                        isExpanded.value = false
                        onFilterSelected(item)
                    }
                )
            }
            
        }
    }
}

@Preview
@Composable
fun FilterForTypePreview(){
    FilterForType(listOf("Todo","habitos","salud")){

    }
}