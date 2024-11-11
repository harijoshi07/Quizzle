package com.example.quizgame.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizgame.R
import com.example.quizgame.data.itemNavigation
import com.example.quizgame.presentation.navigation.Screen

@Composable
fun BottomBar(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry?,
    modifier: Modifier = Modifier
) {

    NavigationBar(containerColor = Color.White) {

        itemNavigation.forEach { item ->

            val isSelected =
                item.title == (navBackStackEntry?.destination?.route ?: Screen.Home.route)

            NavigationBarItem(
                selected = isSelected,
                onClick = { navController.navigate(item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.primary_purple),
                    unselectedIconColor = colorResource(id = R.color.secondary_blue),
                    indicatorColor = colorResource(id = R.color.white)
                ),
                icon = {
                    if (item.title != "WhiteSpace") {
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
    //BottomBar()
}