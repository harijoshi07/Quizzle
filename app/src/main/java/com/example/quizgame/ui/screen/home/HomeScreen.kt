package com.example.quizgame.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizgame.R
import com.example.quizgame.ui.component.QuizCard

@Composable
fun HomeScreen(
    navigateToQuiz: (String) -> Unit,
    navigateToCategory: () -> Unit,
) {
    Column(
       //modifier =  Modifier.padding(20.dp)
    ) {
        Column(Modifier.padding(20.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            //TODO: yet to implement
            RecentBoard()
            Spacer(modifier = Modifier.height(16.dp))
            PlayBoard(navigateToCategory)
        }
        PopularList(navigateToQuiz, navigateToCategory)
    }
}

@Composable
fun RecentBoard(modifier: Modifier = Modifier) {
    val recentList = listOf("a")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE4EAFF),
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                //modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = if (recentList.isNotEmpty()) "Recent Quiz" else "Let's Play Quiz",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )

                if (recentList.isNotEmpty()) {
                    Text(
                        text = "History - Education",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.text_purple)
                    )
                }


            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = colorResource(id = R.color.text_purple)
            )
        }
    }
}

@Composable
fun PlayBoard(navigateToCategory: () -> Unit, modifier: Modifier = Modifier) {

    Surface(
        color = Color(0x4DF1F4FF),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Embark on a journey of collective discovery, where challenges uplift knowledge universally",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = navigateToCategory,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = colorResource(id = R.color.text_purple)
                )
            ) {
                Text(
                    text = "Let's Play",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
fun PopularList(
    navigateToQuiz: (String) -> Unit,
    navigateToCategory: () -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        color = colorResource(id = R.color.white_background),
        shape = RoundedCornerShape(topStartPercent = 16, topEndPercent = 16),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            Modifier
                .padding(20.dp)
                .padding(top = 16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Popular Quiz",
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp
                )
                Text(
                    text = "See All",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.text_purple),
                    modifier = Modifier.clickable { navigateToCategory() }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            QuizCard("Education", "Geography", navigateToQuiz)
            QuizCard("Show Biz", "Video Games", navigateToQuiz)

        }

    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen({}, {})
    //RecentBoard()
    //PlayBoard()
    //PopularList()
}