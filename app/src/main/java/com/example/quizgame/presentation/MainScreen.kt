package com.example.quizgame.presentation

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

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {TopBar(title = "home") },
        floatingActionButton = { FloatingButton()},
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar()},
        containerColor = colorResource(id = R.color.primary_purple)
    ) {
        it
    }

}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()

}