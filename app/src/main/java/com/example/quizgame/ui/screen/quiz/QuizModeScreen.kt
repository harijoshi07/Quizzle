package com.example.quizgame.ui.screen.quiz

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.example.quizgame.R
import com.example.quizgame.data.SettingsQuiz
import com.example.quizgame.data.iconCategory
import com.example.quizgame.data.local.HistoryEntity
import com.example.quizgame.data.remote.response.ResultsItem
import com.example.quizgame.ui.component.AlertDialogComponent
import com.example.quizgame.utils.countMatchingElements
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizModeScreen(
    quizName: String,
    category: String,
    settingsQuiz: SettingsQuiz,
    navigateToHome: () -> Unit,
    navigateToResult: (Int, Int) -> Unit,
    quizViewModel: QuizViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_purple))
    ) {

        LaunchedEffect(key1 = settingsQuiz) {
            quizViewModel.quiz(settingsQuiz)
        }

        //It uses QuizViewModel to load quiz data, displays loading/error states,
        //and shows quiz questions once the data is loaded
        quizViewModel.quiz.collectAsState().value.let { quiz ->

            when {
                quiz.loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.loading),
                            contentDescription = null
                        )
                    }
                }

                quiz.error != null -> {
                    Toast.makeText(LocalContext.current, quiz.error, Toast.LENGTH_SHORT).show()
                }

                quiz.data != null -> {
                    val pagerState = rememberPagerState {
                        quiz.data.size
                    }

                    val answer = remember { mutableListOf<String>() }
                    val dialogState = remember { mutableStateOf(false) }

                    QuizProgressComponent(
                        quiz.data.size,
                        pagerState,
                        dialogState,
                        navigateToHome
                    )
                    QuizQuestionComponent(
                        quizViewModel,
                        quiz.data,
                        pagerState,
                        answer,
                        quizName,
                        category,
                        navigateToResult
                    )
                }
            }
        }
    }
}

@Composable
fun QuizProgressComponent(
    total: Int,
    pagerState: PagerState,
    dialogState: MutableState<Boolean>,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(id = R.color.orange_primary))
        ) {
            Text(
                text = "${pagerState.currentPage + 1} / $total",
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        LinearProgressIndicator(
            progress = (pagerState.currentPage + 1).toFloat() / total.toFloat(),
            color = Color.White,
            trackColor = colorResource(id = R.color.white_transparent),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(5.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { dialogState.value = true },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.white_transparent)
            ),
            contentPadding = PaddingValues(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .border(width = 2.dp, color = Color.White)
            )
        }

        AlertDialogComponent(
            dialogState = dialogState,
            navigateToHome = navigateToHome
        )

    }
}

@Composable
fun QuizQuestionComponent(
    quizViewModel: QuizViewModel,
    quiz: List<ResultsItem>,
    pagerState: PagerState,
    answer: MutableList<String>,
    quizName: String,
    category: String,
    navigateToResult: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val gson = Gson()

    //This code prepares answer choices for a single question in a quiz by combining incorrect
    // and correct answers, then placing the correct answer at a random position within the list.
    val incorrectAnswers = quiz[pagerState.currentPage].incorrectAnswers
    val combinedAnswers = mutableListOf<String>()
    combinedAnswers.addAll(incorrectAnswers)
    val correctAnswer = quiz.map { it.correctAnswer }
    val randomIndex = (0 until combinedAnswers.size).random()
    combinedAnswers.add(randomIndex, quiz[pagerState.currentPage].correctAnswer)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white_background)
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = colorResource(id = R.color.primary_blue),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = iconCategory[category] ?: R.drawable.box_stype
                        ),
                        contentDescription = "",
                        tint = colorResource(id = R.color.primary_purple),
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = quizName,
                    maxLines = 1,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPager(state = pagerState, userScrollEnabled = false) {
                Column {
                    Text(
                        text = "QUESTION ${pagerState.currentPage + 1} OF ${quiz.size}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.text_gray)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = buildAnnotatedString {
                            append(
                                HtmlCompat.fromHtml(
                                    quiz[pagerState.currentPage].question,
                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                                )
                            )
                        },
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    val isSelected = remember {
                        mutableStateOf(false)
                    }
                    //lazy column
                    LazyColumn(modifier = Modifier.fillMaxHeight(0.9f)) {

                        items(combinedAnswers) {
                            isSelected.value =
                                answer.size > pagerState.currentPage && answer[pagerState.currentPage] == it

                            Button(
                                onClick = {
                                    answer.add(pagerState.currentPage, it)
                                    if (answer.size > pagerState.currentPage + 1) {
                                        answer.removeAt(pagerState.currentPage + 1)
                                    }
                                    isSelected.value =
                                        answer.size > pagerState.currentPage && answer[pagerState.currentPage] == it
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = if (isSelected.value) R.color.primary_purple else R.color.white),
                                    contentColor = colorResource(id = if (isSelected.value) R.color.white else R.color.black)
                                ),
                                border = BorderStroke(
                                    width = 0.5.dp,
                                    color = colorResource(id = R.color.black)
                                ),
                                contentPadding = PaddingValues(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                //if long, 16 sp; if short, 20 sp
                                Text(
                                    text = buildAnnotatedString {
                                        append(
                                            HtmlCompat.fromHtml(
                                                it,
                                                HtmlCompat.FROM_HTML_MODE_LEGACY
                                            ).toString()
                                        )
                                    },
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth()
                                )

                            }

                        }
                    }
                }
            }

            val isSubmit = pagerState.pageCount == pagerState.currentPage + 1

            Row {
                if (pagerState.currentPage != 0) {
                    IconButton(
                        onClick = { scope.launch { pagerState.scrollToPage(pagerState.currentPage - 1) } },
                        modifier = Modifier
                            .border(
                                width = 1.5.dp,
                                color = colorResource(id = R.color.primary_purple),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "back",
                            tint = colorResource(id = R.color.primary_purple)
                        )
                    }
                }


                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (answer.size != pagerState.currentPage + 1) {

                            Toast.makeText(
                                context, "Please select an answer", Toast.LENGTH_SHORT
                            ).show()

                        } else if (!isSubmit) {

                            scope.launch { pagerState.scrollToPage(pagerState.currentPage + 1) }
                        } else {

                            val countMatch = countMatchingElements(correctAnswer, answer)
                            val answerSize = answer.size
                            val data = HistoryEntity(
                                id = 0,
                                quiz = quizName,
                                category = category,
                                correctAnswer = countMatch,
                                size = answerSize,
                                question = gson.toJson(quiz),
                                correctAnswerData = gson.toJson(correctAnswer),
                                userAnswerData = gson.toJson(answer)
                            )

                            quizViewModel.insertHistory(data)
                            navigateToResult(countMatch, answerSize)
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary_purple)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                ) {

                    Text(text = if (isSubmit) "Submit" else "Next", fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuizModeScreenPreview() {
    QuizModeScreen(
        quizName = "",
        category = "",
        settingsQuiz = SettingsQuiz(),
        navigateToHome = {},
        navigateToResult = { a, b -> }
    )
    //QuizProgressComponent()
    //QuizQuestionComponent()
}