package com.example.quizgame.ui.screen.quiz

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizgame.R

@Composable
fun QuizModeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_purple))
    ) {
        QuizProgressComponent()
        QuizQuestionComponent()
    }

}

@Composable
fun QuizProgressComponent(modifier: Modifier = Modifier) {

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
                text = "4 / 10",
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        LinearProgressIndicator(
            progress = { 0.4f },
            color = Color.White,
            trackColor = colorResource(id = R.color.white_transparent),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(5.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { /*TODO*/ },
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
    }
}

@Composable
fun QuizQuestionComponent(modifier: Modifier = Modifier) {

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
                        painter = painterResource(id = R.drawable.games),
                        contentDescription = "",
                        tint = colorResource(id = R.color.primary_purple),
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Video Games",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "QUESTION 4 OF 10",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.text_gray)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "What is the name of the virus in ”Metal Gear Solid” ?",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            //lazy column
            Column(modifier = Modifier.fillMaxHeight(0.9f)) {
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.white),
                        contentColor = colorResource(id = R.color.black)
                    ),
                    border = BorderStroke(
                        width = 0.5.dp,
                        color = colorResource(id = R.color.black)
                    ),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    //if long, 16 sp; if short, 20 sp
                    Text(
                        text = "FOXDIE",
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            }

            Row {
                IconButton(
                    onClick = { /*TODO*/ },
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

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary_purple)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                ) {

                    Text(text = "Next", fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuizModeScreenPreview() {
    QuizModeScreen()
    //QuizProgressComponent()
    //QuizQuestionComponent()
}