package com.practica.habitos.ui.components.hoyScreenComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    title: String,
    onMenuClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onSearchIconClick: () -> Unit,
    onDeleteIconClick: () -> Unit,
    onBackClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var isSearchActive by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (isSearchActive) {
                BasicTextField(
                    value = searchQuery,
                    onValueChange = { value ->
                        searchQuery = value
                        onSearchQueryChanged(value.text)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    decorationBox = @Composable { innerTextField ->
                    }
                )
            } else {
                Text(text = title)
            }
        },
        navigationIcon = {
            if (isSearchActive) {
                IconButton(onClick = {
                    isSearchActive = false
                    searchQuery = TextFieldValue("")
                    onBackClick()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            } else {
                IconButton(onClick = onMenuClick) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            }
        },
        actions = {
            if (!isSearchActive) {
                IconButton(onClick = {
                    isSearchActive = true
                    onSearchIconClick()
                }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun SearchTopAppBarPreview() {
    SearchTopAppBar(
        title = "actividad",
        onMenuClick = { /*TODO*/ },
        onSearchQueryChanged = {},
        onSearchIconClick = { /*TODO*/ },
        onDeleteIconClick = { /*TODO*/ },
    ) {
//
    }
}
