package com.example.quizgame.ui.screen.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizgame.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizResultScreen(
    id: Int,
    correctAnswer: Int,
    size: Int,
    quiz: String,
    navigateToQuiz: (String) -> Unit,
    navigateToHome: () -> Unit,
    navigateToAnswer: (Int) -> Unit,
    quizViewModel: QuizViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    if (id != 0) {
        QuizResultContent(
            id,
            correctAnswer,
            size,
            quiz,
            navigateToQuiz,
            navigateToHome,
            navigateToAnswer
        )
    } else {
        quizViewModel.getLatestHistory()
        quizViewModel.getLatestHistory.collectAsState().value.let {
            when {
                it.loading -> {
                    Text(text = "Loading")
                }
                it.data != null -> {
                    QuizResultContent(
                        id = it.data.id,
                        correctAnswer = it.data.correctAnswer,
                        size = it.data.size,
                        quiz = it.data.quiz,
                        navigateToQuiz = navigateToQuiz,
                        navigateToHome = navigateToHome,
                        navigateToAnswer = navigateToAnswer
                    )
                }
                it.error != null -> {
                    Text(text = it.error)
                }
            }
        }
    }

}

@Composable
fun QuizResultContent(
    id: Int,
    correctAnswer: Int,
    size: Int,
    quiz: String,
    navigateToQuiz: (String) -> Unit,
    navigateToHome: () -> Unit,
    navigateToAnswer: (Int) -> Unit
) {

    Column(
        modifier = Modifier.background(color = colorResource(id = R.color.primary_purple))
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.white)
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {

                BoxResult(correctAnswer, size, quiz, navigateToQuiz)

                Column {
                    Button(
                        onClick = {
                            navigateToAnswer(id)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.primary_purple)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Show Answer", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = navigateToHome,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.primary_purple)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(50.dp)
                    ) {

                        Text(
                            text = "Close",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }

}


@Composable
fun BoxResult(
    correctAnswer: Int,
    size: Int,
    quiz: String,
    navigateToQuiz: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = Modifier.padding(16.dp)) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.primary_purple)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.4f)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.trophy),
                    contentDescription = "",
                    modifier = Modifier.size(160.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { navigateToQuiz(quiz) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.white_transparent)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp)
                ) {

                    Text(
                        text = "Play Again",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(8.dp)) {

            Row {
                TextResult(title = "CORRECT", value = "$correctAnswer")
                Spacer(modifier = Modifier.weight(1f))
                TextResult(title = "INCORRECT", value = "${size - correctAnswer}")

            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                TextResult(
                    title = "RESULT",
                    value = "${if (correctAnswer == 0) 0 else (correctAnswer * 100) / size}%"
                )
            }
        }
    }
}

@Composable
fun TextResult(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.text_gray),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
private fun QuizResultScreenPreview() {

    //QuizResultScreen(correctAnswer = 1, size = 5, quiz = "Geography", {}, {})
    //BoxResult()
    //TextResult(title = "title", value = "value")

}