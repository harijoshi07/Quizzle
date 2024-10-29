package com.example.quizgame.ui.screen.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizgame.R

@Composable
fun QuizSelectScreen(modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_purple))
    ) {

        Image(
            painter = painterResource(id = R.drawable.option),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(16.dp))

        QuizSelectComponent()
    }

}

@Composable
fun QuizSelectComponent(modifier: Modifier = Modifier) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white_background)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Quiz Select",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            InputType()

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary_purple),
                    contentColor = colorResource(id = R.color.white_background)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Let's Play", fontSize = 20.sp)
            }
        }
    }
}


@Composable
fun InputType(modifier: Modifier = Modifier) {

    Spacer(modifier = Modifier.height(16.dp))

    Column {
        Text(
            text = "Number of Questions",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.tertiary_blue),
                    contentColor = colorResource(id = R.color.black)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.baseline_question_mark_24),
                                contentDescription = "",
                                tint = colorResource(id = R.color.primary_purple),
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "10 Questions",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.dropdown),
                        contentDescription = ""
                    )
                }
            }

            DropdownMenu(
                expanded = true,
                onDismissRequest = { /*TODO*/ }) {

                DropdownMenuItem(
                    text = { Text(text = "Ad") },
                    onClick = { /*TODO*/ }
                )
            }
        }
    }
}


@Preview
@Composable
private fun QuizOptionScreenPreview() {

    QuizSelectScreen()
    //QuizOptionComponent()
    //InputType()


}