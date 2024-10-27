package com.example.quizgame.data

import com.example.quizgame.R

data class BottomBarNavigationItem(
    val title: String,
    val icon: Int
)

val itemNavigation = listOf(
    BottomBarNavigationItem(title = "Home", icon = R.drawable.home),
    BottomBarNavigationItem(title = "Category", icon = R.drawable.categories),
    BottomBarNavigationItem(title = "WhiteSpace", icon = R.drawable.rocket),
    BottomBarNavigationItem(title = "History", icon = R.drawable.history),
    BottomBarNavigationItem(title = "Profile", icon = R.drawable.profile)
)