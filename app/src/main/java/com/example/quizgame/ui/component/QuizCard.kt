package com.example.quizgame.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizgame.R

@Composable
fun QuizCard(modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, color = colorResource(id = R.color.secondary_blue))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                Surface(
                    color = colorResource(id = R.color.primary_blue),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.games),
                        contentDescription = "logo",
                        modifier = Modifier.padding(10.dp),
                        tint = colorResource(id = R.color.primary_purple)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Video Games",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = "Entertaiment",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Quiz-play",
                modifier = Modifier.size(30.dp),
                tint = colorResource(id = R.color.text_purple)
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))

}


@Preview
@Composable
private fun QuizCardPreview() {
    QuizCard()

}