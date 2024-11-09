package com.example.quizgame.ui.screen.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizgame.R
import com.example.quizgame.data.categoryQuizList
import com.example.quizgame.ui.component.QuizCard

@Composable
fun CategorySelectScreen(
    quizCategory: String,
    navigateQuiz: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8),
        color = colorResource(id = R.color.white_background),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = quizCategory,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(categoryQuizList[quizCategory]!!) { quizCategoryItem ->
                    QuizCard(
                        category = quizCategory,
                        quizCategory = quizCategoryItem,
                        navigateToQuiz = { navigateQuiz(quizCategoryItem) })
                }

            }

        }
    }

}

@Preview
@Composable
private fun CategorySelectScreenPreview() {

    CategorySelectScreen(quizCategory = "Education", navigateQuiz = {})

}