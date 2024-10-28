package com.example.quizgame.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizgame.R
import com.example.quizgame.ui.component.BottomBar
import com.example.quizgame.ui.component.FloatingButton
import com.example.quizgame.ui.component.TopBar
import com.example.quizgame.ui.screen.category.CategoryScreen
import com.example.quizgame.ui.screen.home.HomeScreen
import com.example.quizgame.ui.screen.profile.ProfileScreen
import com.example.quizgame.ui.screen.shared.ListScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = { TopBar(title = "home") },
        floatingActionButton = { FloatingButton() },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar() },
        containerColor = colorResource(id = R.color.primary_purple)
    ) {
        //HomeScreen(it)
        //CategoryScreen(modifier = Modifier.padding(it))
        //ListScreen(modifier = Modifier.padding(it))
        ProfileScreen(modifier = Modifier.padding(it))
    }

}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()

}