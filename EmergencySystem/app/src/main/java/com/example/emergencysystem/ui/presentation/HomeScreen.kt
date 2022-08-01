@file:OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class
)

package com.example.emergencysystem.ui.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.emergencysystem.R
import com.example.emergencysystem.util.Items
import com.example.emergencysystem.util.allItems
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar()
        },
        content =  {
            HomeScreenContent(navController)
        }
    )
}

@Composable
fun HomeScreenContent(navController: NavController) {
    Column {
        Info()
        LazyVerticalGrid(cells = GridCells.Adaptive(150.dp)) {
            items(allItems) {
                ArticleCard(items = it) {id ->
                    navController.navigate(it.route + "/$id")
                }
            }
        }
    }
}


@Composable
fun Info() {
    Row (modifier = Modifier.fillMaxWidth()){
        Text(
            text = "What are you looking for?",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colors.onSurface,
        )
    }
}

@Composable
fun ArticleCard(items: Items, onClick : (Int) -> Unit) {
    Card(elevation = 16.dp,
        modifier = Modifier
            .clickable { onClick(items.id) }
            .padding(16.dp)
    ) {
        Column {
            Image(
                painterResource(items.image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp),
                contentScale = ContentScale.Crop)
            Text(text = items.text, style = MaterialTheme.typography.h6)
        }

    }

}

@Composable
fun TopBar() {
TopAppBar(
        title = {  },
    backgroundColor = Color(0xffffffff),
        navigationIcon = {
                IconButton(
                    onClick = { /* do something */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = ""
                    )
                }

        },
        actions = {
            IconButton(
                onClick = { /* do something */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.bell),
                    contentDescription = "", modifier = Modifier.height(50.dp),
                    tint = Color(0xffffd11a)
                )
            }
        },
    modifier = Modifier.height(100.dp)
)
}