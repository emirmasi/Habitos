package com.practica.habitos.ui.components.navigationComponent

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica.habitos.R
import com.practica.habitos.ui.theme.Rosadito
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
* app bar necesita el titulo nomas , porque el drawer state siempre va a ser el mismo, lo mejor es pasarle
* el titulo y las actions , el drawer lo tiene por defecto y el titulo */
@ExperimentalMaterial3Api
@Composable
fun CustomTopAppBar(
    drawerState: DrawerState,
    scope: CoroutineScope,
    title: String,
    actions: @Composable ((RowScope) -> Unit),
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_drawer),
                    contentDescription = null,
                    modifier =
                        Modifier
                            .size(42.dp),
                    tint = Rosadito,
                )
            }
        },
        actions = actions,
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
}
