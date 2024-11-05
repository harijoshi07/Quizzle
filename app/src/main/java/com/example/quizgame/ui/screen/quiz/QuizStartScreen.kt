package com.example.quizgame.ui.screen.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import com.example.quizgame.data.quizCategoryDescription

@Composable
fun QuizStartScreen(quiz: String, modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_purple))

    ) {
        Image(
            painter = painterResource(id = R.drawable.new_question),
            contentDescription = "image data"
        )
        Spacer(modifier = Modifier.height(16.dp))

        QuizContent(quiz)
    }

}


@Composable
fun QuizContent(quiz: String, modifier: Modifier = Modifier) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white_background),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            ContentHeader()
            Spacer(modifier = Modifier.height(16.dp))
            QuizType()
            Spacer(modifier = Modifier.height(16.dp))
            ContentDescription(quiz)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.white_background),
                    containerColor = colorResource(id = R.color.primary_purple)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Let's Play", fontSize = 20.sp)
            }
        }
    }

}

@Composable
fun ContentHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = "Entertaiment",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Video Games",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        IconButton(
            modifier = Modifier
                .size(52.dp)
                .border(
                    width = 4.dp,
                    color = colorResource(id = R.color.secondary_blue),
                    shape = CircleShape
                ),
            onClick = { /*TODO*/ },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = colorResource(id = R.color.white_background),
                containerColor = colorResource(id = R.color.primary_purple)
            )
        ) {
            Icon(
                modifier = Modifier.size(36.dp),
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Settings"
            )
        }
    }
}

@Composable
fun QuizType(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.tertiary_blue)
            )
            .padding(12.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {

            BoxType(painter = R.drawable.baseline_question_mark_24, text = "10 Question")
            Spacer(modifier = Modifier.width(30.dp))
            BoxType(painter = R.drawable.baseline_ballot_24, text = "Medium")
        }

        Spacer(modifier = Modifier.height(16.dp))

        BoxType(painter = R.drawable.box_type, text = "Mixed")
    }

}

@Composable
fun BoxType(painter: Int, text: String) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp))
                .background(color = colorResource(id = R.color.white))
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp),
                painter = painterResource(id = painter),
                contentDescription = text,
                tint = colorResource(id = R.color.primary_purple)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ContentDescription(category: String, modifier: Modifier = Modifier) {

    Column {

        Text(
            text = "Description",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = quizCategoryDescription[category]!!,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

}


@Preview
@Composable
private fun QuizStartScreenPreview() {

    QuizStartScreen(quiz = "History")
    //QuizContent()
    //ContentHeader()
    //QuizType()
    //BoxType(painter = R.drawable.trophy, text = "")
    //ContentDescription(category = "History")

}