package com.example.quizgame.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quizgame.R
import com.example.quizgame.presentation.navigation.Screen
import com.example.quizgame.ui.component.BottomBar
import com.example.quizgame.ui.component.FloatingButton
import com.example.quizgame.ui.component.TopBar
import com.example.quizgame.ui.screen.category.CategoryScreen
import com.example.quizgame.ui.screen.home.HomeScreen
import com.example.quizgame.ui.screen.profile.ProfileScreen
import com.example.quizgame.ui.screen.shared.ListScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = { TopBar(navBackStackEntry = navBackStackEntry) },
        floatingActionButton = { FloatingButton() },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomBar(
                navController = navController,
                navBackStackEntry = navBackStackEntry
            )
        },
        containerColor = colorResource(id = R.color.primary_purple)
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Screen.Home.route) {

            composable(route = Screen.Home.route) {
                HomeScreen(innerPadding = innerPadding)
            }

            composable(route = Screen.Category.route) {
                CategoryScreen(modifier = Modifier.padding(innerPadding))
            }

            composable(route = Screen.Profile.route) {
                ProfileScreen(modifier = Modifier.padding(innerPadding))
            }

            composable(route = Screen.List.route) {
                ListScreen(modifier = Modifier.padding(innerPadding))
            }

        }
    }

}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()

}