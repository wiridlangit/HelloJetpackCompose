package com.jsc.hellojetpackcompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jsc.hellojetpackcompose.Item
import com.jsc.hellojetpackcompose.ui.components.ListItem

@Composable
fun MainScreen(navController: NavController, itemList: List<Item>, modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }
    val filteredList = itemList.filter { it.name.contains(searchText, ignoreCase = true) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_item_screen") }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Item")
            }
        },
        content = { padding ->
            Column(modifier = modifier.fillMaxSize().padding(padding)) {
                TopBar(navController = navController, searchText = searchText, onSearchTextChanged = { newText ->
                    searchText = newText
                })

                LazyColumn(modifier = modifier) {
                    items(filteredList.size) { index ->
                        ListItem(
                            name = filteredList[index].name,
                            image = filteredList[index].image
                        ) {
                            navController.navigate("detail_screen/$index")
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val sampleItems = listOf(
        Item("Andre", "Description of Andre"),
        Item("Desta", "Description of Desta"),
        Item("Parto", "Description of Parto"),
        Item("Vincent", "Description of Vincent")
    )
    val navController = rememberNavController()

    MainScreen(navController = navController, itemList = sampleItems)
}
