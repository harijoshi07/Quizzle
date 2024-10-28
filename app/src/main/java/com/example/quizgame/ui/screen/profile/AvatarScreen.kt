package com.example.quizgame.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import com.example.quizgame.data.avatarProfile
import com.example.quizgame.data.backgroundProfile


@Composable
fun AvatarScreen(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(20.dp)
    ) {
        Text(
            text = "Choose Avatar",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        AvatarBox()

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        ColorBox()

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.primary_purple)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Apply",
                fontSize = 24.sp
            )

        }
    }
}


@Composable
fun AvatarBox(modifier: Modifier = Modifier) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        items(8) {
            Surface(
                color = colorResource(id = R.color.gray_background),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .height(60.dp)
                    .width(120.dp)
                    .padding(horizontal = 4.dp)
                    .then(
                        if (1 == it) Modifier.border(
                            2.5.dp,
                            colorResource(id = R.color.primary_purple),
                            shape = RoundedCornerShape(20)
                        ) else Modifier
                    )
            ) {
                Image(
                    painter = painterResource(id = avatarProfile[it]!!),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 10.dp)
                )
            }
        }

    }

}


@Composable
fun ColorBox(modifier: Modifier = Modifier) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.padding(12.dp)
    ) {

        items(8) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(40.dp)
                    .clip(shape = CircleShape)
                    .background(color = colorResource(id = backgroundProfile[it]!!))
                    .then(
                        if (6 == it) {
                            Modifier.border(
                                2.5.dp,
                                colorResource(id = R.color.primary_purple),
                                shape = CircleShape
                            )
                        } else {
                            Modifier
                        }
                    )
            )
        }
    }
}

@Preview
@Composable
fun AvatarScreenPreview(modifier: Modifier = Modifier) {
    AvatarScreen()
    //AvatarBox()
    //ColorBox()

}