package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica.habitos.R
import com.practica.habitos.domain.models.Categoria

// todo: añadir parametro de las categorias
@Composable
fun SearchBoxComponent(
    label: String,
    searchBoxState: State<Boolean>,
    listOfCategory: List<Categoria>,
    onSearch: (String) -> Unit,
) {
    // aca deberia ir el codigo de search box...
    val text =
        remember {
            mutableStateOf("")
        }
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(90.dp)
                .clickable {
                    // Todo: componentes para elegir la categoria , le mandamos
                },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text(
                text = "Selecciona una categoria",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(),
                textAlign = TextAlign.Center,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                TextField(
                    value = text.value,
                    onValueChange = { newValue ->
                        text.value = newValue
                        onSearch(newValue)
                    },
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.search_24),
                                contentDescription = null,
                            )
                        }
                    },
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                    //verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ){
                    IconButton(onClick = { /*TODO:guardar filtros*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.push_pin_24),
                            contentDescription = null,
                        )
                    }
                    IconButton(onClick = { /*TODO:borrar filtros*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete_outline_24),
                            contentDescription = null,
                        )
                    }
                    IconButton(onClick = { /*TODO:salir de la busqueda*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_up),
                            contentDescription = null,
                        )
                    }
                }
                
            }
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
        onSearch = {},
    )
}
