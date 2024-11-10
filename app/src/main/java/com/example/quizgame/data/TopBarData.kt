package com.example.quizgame.data

import com.example.quizgame.presentation.navigation.Screen

val titleTopBar = mapOf(
    Screen.QuizMode.route + "/{quiz}/{category}" to "Quiz Mode",
    Screen.QuizSelect.route to "Quiz Select",
    Screen.QuizStart.route + "/{quizCategory}/{quiz}" to "Quiz Start",
    Screen.QuizQuestion.route to "Quiz Question",
    Screen.QuizResult.route + "/{correctAnswer}/{size}/{quiz}/{quizCategory}" to "Quiz Result",
    Screen.SelectQuiz.route + "/{quizCategory}" to "Select Quiz",
    Screen.List.route to "List",
    Screen.Home.route to "Home",
    Screen.Category.route to "Category",
    Screen.Profile.route to "Profile",
    Screen.History.route to "History",
    Screen.Main.route to "Main",
    Screen.OnBoarding.route to "OnBoarding",
    Screen.Welcome.route to "Welcome",
    Screen.Login.route to "Login",
    Screen.Register.route to "Register"
)