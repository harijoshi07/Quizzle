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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.quizgame.data.SettingsQuiz

@Composable
fun QuizSelectScreen(navigateToStart: (SettingsQuiz) -> Unit, modifier: Modifier = Modifier) {

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

        QuizSelectComponent(navigateToStart)
    }

}

@Composable
fun QuizSelectComponent(navigateToStart: (SettingsQuiz) -> Unit, modifier: Modifier = Modifier) {

    var amount by remember { mutableIntStateOf(10) }
    var type by remember { mutableStateOf("multiple") }
    var difficulty by remember { mutableStateOf("easy") }

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

            InputType(
                onClick = {
                    amount = it.toInt()
                },
                listType = listOf("10", "20", "30"),
                title = "Number of Questions",
                data = amount.toString(),
                painter = R.drawable.baseline_question_mark_24
            )

            InputType(
                onClick = {
                    difficulty = it
                },
                listType = listOf("easy", "medium", "hard", "mixed"),
                title = "Difficulty of Questions",
                data = difficulty,
                painter = R.drawable.lv
            )

            InputType(
                onClick = {
                    type = it
                },
                listType = listOf("mixed", "multiple", "boolean"),
                title = "Type of Questions",
                data = type,
                painter = R.drawable.box_stype
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navigateToStart(
                        SettingsQuiz(amount = amount, type = type, difficulty = difficulty)
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary_purple),
                    contentColor = colorResource(id = R.color.white_background)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save", fontSize = 20.sp)
            }
        }
    }
}


@Composable
fun InputType(
    onClick: (String) -> Unit,
    listType: List<String>,
    title: String,
    data: String,
    painter: Int,
    modifier: Modifier = Modifier
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box {
            Button(
                onClick = { isExpanded = true },
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
                                painter = painterResource(id = painter),
                                contentDescription = "",
                                tint = colorResource(id = R.color.primary_purple),
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = data,
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
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {

                listType.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it) },
                        onClick = {
                            onClick(it)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun QuizOptionScreenPreview() {

    QuizSelectScreen(navigateToStart = {})
    //QuizOptionComponent()
    //InputType()


}