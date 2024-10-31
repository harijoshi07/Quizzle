package com.example.quizgame.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizgame.presentation.MainScreen
import com.example.quizgame.ui.screen.auth.LoginScreen
import com.example.quizgame.ui.screen.auth.RegisterScreen
import com.example.quizgame.ui.screen.auth.WelcomeScreen
import com.example.quizgame.ui.screen.onboarding.OnBoardingScreen

@Composable
fun NavigationScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main.route) {

        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen(navController)
        }

        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(navController)
        }

        composable(route = Screen.Main.route) {
            MainScreen()
        }


    }

}