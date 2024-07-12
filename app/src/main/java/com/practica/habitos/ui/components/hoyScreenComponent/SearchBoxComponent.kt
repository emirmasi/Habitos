package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practica.habitos.R
import com.practica.habitos.domain.models.Categoria
import com.practica.habitos.ui.theme.Rosadito
import com.practica.habitos.ui.theme.primaryContainerLight

// todo: añadir parametro de las categorias
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBoxComponent(
    label: String,
    searchBoxState: State<Boolean>,
    listOfCategory: List<Categoria>,
    onFilterForType: (String)->Unit,
    onFilterByCategory: (String)->Unit,
    onSaveFilter: (String)->Unit,
    onDeleteFilter:()->Unit,
    onBack:()->Unit,
    onSearch: (String) -> Unit,
) {
    // aca deberia ir el codigo de search box...
    val text =
        remember {
            mutableStateOf("")
        }
    val openFilterByCategory = remember {
        mutableStateOf(false)
    }
    val openFilterForType = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(90.dp)
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .width(130.dp)
                    .border(BorderStroke(1.dp, Color.Black))
                    .padding(start = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                ///esto es un listPreview
                Text(
                    text = "Todo",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_down),
                        contentDescription = null,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(BorderStroke(1.dp, Color.Black))
                    .clickable {
                        //todo: componenente para seleccionar una categoria
                    },
                contentAlignment = Alignment.Center
            ){
                ///esto es un listPreview
                Text(
                    text = "Selecciona una categoria",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Row (modifier = Modifier
            .fillMaxSize()
            .height(45.dp)
        ){
            TextField(
                value = text.value,
                onValueChange = {newText ->
                    text.value = newText
                    onSearch(newText)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(1.dp, Color.Black),shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
                placeholder = {
                    Text(text = label)
                },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search_24),
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Row(){
                        IconButton(onClick = {
                            onSaveFilter(text.value)
                        }){
                            Icon(
                                painter = painterResource(id = R.drawable.push_pin_24),
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = {
                            onDeleteFilter()
                        }){
                            Icon(
                                painter = painterResource(id = R.drawable.delete_outline_24),
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = {
                            onBack()
                        }){
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_up),
                                contentDescription = null
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
                colors = TextFieldDefaults.colors(
                    cursorColor = Rosadito,//para cambiar el cursor del textfield
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = primaryContainerLight,
                    unfocusedContainerColor = primaryContainerLight,///este es para cambiar el fondo del textfield
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBoxComponentPreview() {
    // aca debemos hacer la
    SearchBoxComponent(
        label = "Buscar",
        searchBoxState = remember { mutableStateOf(false) },
        listOfCategory = emptyList(),
        onFilterForType = {},
        onFilterByCategory = {},
        onSaveFilter = {},
        onDeleteFilter = {},
        onBack = {},
        onSearch = {},
    )
}
