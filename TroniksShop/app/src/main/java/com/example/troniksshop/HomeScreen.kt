package com.example.troniksshop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Welcome")
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Shopping cart")
            }
        }
        TextField(value = , onValueChange = )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}