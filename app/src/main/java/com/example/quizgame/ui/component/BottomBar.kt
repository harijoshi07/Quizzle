package com.example.quizgame.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizgame.data.itemNavigation

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    NavigationBar(containerColor = Color.White) {
        itemNavigation.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    if (item.title!="WhiteSpace"){
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            )

        }

    }

}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar()
}