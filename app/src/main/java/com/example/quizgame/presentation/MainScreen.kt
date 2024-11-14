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
import com.example.quizgame.data.SettingsQuiz
import com.example.quizgame.presentation.navigation.Screen
import com.example.quizgame.ui.component.BottomBar
import com.example.quizgame.ui.component.FloatingButton
import com.example.quizgame.ui.component.TopBar
import com.example.quizgame.ui.screen.category.CategoryScreen
import com.example.quizgame.ui.screen.category.CategorySelectScreen
import com.example.quizgame.ui.screen.history.HistoryScreen
import com.example.quizgame.ui.screen.home.HomeScreen
import com.example.quizgame.ui.screen.profile.ProfileScreen
import com.example.quizgame.ui.screen.quiz.QuizAnswerResultScreen
import com.example.quizgame.ui.screen.quiz.QuizModeScreen
import com.example.quizgame.ui.screen.quiz.QuizResultScreen
import com.example.quizgame.ui.screen.quiz.QuizSelectScreen
import com.example.quizgame.ui.screen.quiz.QuizStartScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            TopBar(
                navBackStackEntry = navBackStackEntry,
                navigateBack = { navController.popBackStack() })
        },
        floatingActionButton = {
            if (navBackStackEntry?.destination?.route?.startsWith("Quiz") == false) {
                FloatingButton(navigateToCategory = { navController.navigate(Screen.Category.route) })
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            if (navBackStackEntry?.destination?.route?.startsWith("Quiz") == false) {
                BottomBar(
                    navController = navController,
                    navBackStackEntry = navBackStackEntry
                )
            }
        },
        containerColor = colorResource(id = R.color.primary_purple)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = Screen.Home.route) {
                HomeScreen(
                    navigateToQuiz = { navController.navigate(route = Screen.SelectQuiz.route + "/$it") },
                    navigateToCategory = { navController.navigate(route = Screen.Category.route) },
                )
            }

            composable(route = Screen.Category.route) {
                CategoryScreen(
                    navigateToQuiz = { navController.navigate(route = Screen.SelectQuiz.route + "/$it") },
                )
            }

            composable(Screen.SelectQuiz.route + "/{quizCategory}") {
                val quizCategory = it.arguments?.getString("quizCategory") ?: ""
                CategorySelectScreen(quizCategory = quizCategory, navigateQuiz = { quiz ->
                    navController.navigate(Screen.QuizStart.route + "/$quizCategory/$quiz")
                })
            }

            composable(route = Screen.Profile.route) {
                ProfileScreen(modifier = Modifier.padding(innerPadding))
            }

            composable(Screen.History.route) {
                HistoryScreen(navigateToHistory = { id,correctAnswer, size, quiz, category ->
                    navController.navigate(
                        Screen.QuizResult.route + "/$id/$correctAnswer/$size/$quiz/$category"
                    )
                })
            }

            //quiz Screen
            composable(Screen.QuizStart.route + "/{quizCategory}/{quiz}") {
                val quizCategory = it.arguments?.getString("quizCategory") ?: ""
                val quiz = it.arguments?.getString("quiz") ?: ""
                val settingsQuiz = it.savedStateHandle.get<SettingsQuiz>("settingsQuiz")

                QuizStartScreen(quizCategory = quizCategory,
                    quiz = quiz,
                    settingsQuiz = settingsQuiz ?: SettingsQuiz(),
                    navigateToSelect = {
                        navController.navigate(Screen.QuizSelect.route)
                    },
                    navigateToGames = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "settingsQuizStart", it
                        )
                        navController.navigate(Screen.QuizMode.route + "/$quiz/$quizCategory")
                    })
            }

            composable(Screen.QuizSelect.route) {
                QuizSelectScreen(navigateToStart = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "settingsQuiz", it
                    )
                    navController.popBackStack()
                })
            }

            composable(Screen.QuizMode.route + "/{quiz}/{category}") {
                val settingsQuizStart =
                    navController.previousBackStackEntry?.savedStateHandle?.get<SettingsQuiz>("settingsQuizStart")
                        ?: SettingsQuiz()
                val quizName = it.arguments?.getString("quiz") ?: ""
                val category = it.arguments?.getString("category") ?: ""
                QuizModeScreen(
                    settingsQuiz = settingsQuizStart,
                    quizName = quizName,
                    category = category,
                    navigateToHome = {navController.navigate(Screen.Home.route)},
                    navigateToResult = { correctAnswer, size ->
                        navController.navigate(
                            Screen.QuizResult.route + "/null/$correctAnswer/$size/$quizName/$category"
                        )
                    })
            }

            composable(Screen.QuizResult.route + "/{id}/{correctAnswer}/{size}/{quiz}/{quizCategory}") {
                val correctAnswer = it.arguments?.getString("correctAnswer")?.toInt() ?: 0
                val size = it.arguments?.getString("size")?.toInt() ?: 0
                val quiz = it.arguments?.getString("quiz") ?: ""
                val category = it.arguments?.getString("quizCategory") ?: ""
                val id = it.arguments?.getString("id")?:"0"
                QuizResultScreen(
                    id = if(id == "null") 0 else id.toInt(),
                    correctAnswer = correctAnswer,
                    size = size,
                    quiz = quiz,
                    navigateToQuiz = {
                        navController.navigate(Screen.QuizStart.route + "/$category/$quiz")
                    },
                    navigateToHome = {
                        navController.navigate(Screen.Home.route)
                    },
                    navigateToAnswer = {
                        navController.navigate(Screen.QuizAnswerResult.route + "/$it")
                    }
                )

            }

            composable(Screen.QuizAnswerResult.route + "/{id}") {
                val id = it.arguments?.getString("id") ?:"1"
                QuizAnswerResultScreen(id = id.toInt())
            }

        }
    }

}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()

}