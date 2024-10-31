package com.example.quizgame.ui.screen.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun OnBoardingScreen(navController: NavController, modifier: Modifier = Modifier) {

}

@Preview
@Composable
private fun OnBoardingScreenPreview() {

    OnBoardingScreen(navController = rememberNavController())
}