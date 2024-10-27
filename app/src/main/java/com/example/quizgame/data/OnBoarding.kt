package com.example.quizgame.data

data class OnBoarding(
    val title:String = "",
    val description:String = ""
)
val onBoardingData = mapOf(
    0 to OnBoarding("",""),
    1 to OnBoarding("",""),
    2 to OnBoarding("","")
)