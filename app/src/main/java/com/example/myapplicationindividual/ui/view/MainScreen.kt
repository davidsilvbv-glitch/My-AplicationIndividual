package com.example.myapplicationindividual.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

enum class ApiTab(val title: String) {
    CHUCK_NORRIS("Chuck Norris"),
    STAR_WARS("Star Wars"),
    MARVEL("Marvel")
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = ApiTab.entries

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        label = { Text(text = tab.title) },
                        icon = { Text(text = (index + 1).toString()) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> ChuckNorrisScreen()
                1 -> SwapiScreen()
                2 -> MarvelScreen()
            }
        }
    }
}
