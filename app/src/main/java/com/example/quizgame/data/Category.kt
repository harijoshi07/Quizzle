package com.example.quizgame.data

import com.example.quizgame.R

data class Category(
    val title: String = "",
    val amountQuiz: Int = 0,
    val icon: Int = 0
)

sealed class CategoryQuiz(val title:String){
    data object Education : CategoryQuiz("Education")
    data object Entertainment : CategoryQuiz("Entertainment")
    data object Technology : CategoryQuiz("Technology")
    data object Art : CategoryQuiz("Art")
    data object Science : CategoryQuiz("Science")
    data object Showbiz : CategoryQuiz("Showbiz")
}

val iconCategory = mapOf(
    CategoryQuiz.Entertainment.title to R.drawable.entertainment,
    CategoryQuiz.Education.title to R.drawable.education,
    CategoryQuiz.Technology.title to R.drawable.tech,
    CategoryQuiz.Art.title to R.drawable.art,
    CategoryQuiz.Science.title to R.drawable.science,
    CategoryQuiz.Showbiz.title to R.drawable.showbiz
)

val itemCategory = listOf(
    Category(
        title = CategoryQuiz.Entertainment.title,
        amountQuiz = 4,
        icon = R.drawable.entertainment
    ),
    Category(
        title = CategoryQuiz.Education.title,
        amountQuiz = 4,
        icon = R.drawable.education
    ),
    Category(
        title = CategoryQuiz.Technology.title,
        amountQuiz = 4,
        icon = R.drawable.tech
    ),
    Category(
        title = CategoryQuiz.Art.title,
        amountQuiz = 4,
        icon = R.drawable.art
    ),
    Category(
        title = CategoryQuiz.Science.title,
        amountQuiz = 4,
        icon = R.drawable.science
    ),
    Category(
        title = CategoryQuiz.Showbiz.title,
        amountQuiz = 4,
        icon = R.drawable.showbiz
    )
)
