package com.example.quizgame.ui.screen.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quizgame.R
import com.example.quizgame.ui.component.InputForm
import com.example.quizgame.ui.component.TopBar

@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = { TopBar(navBackStackEntry = navBackStackEntry) },
        containerColor = colorResource(id = R.color.tertiary_blue)
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 30.dp)
        ) {
            InputForm(title = "Email Address", icon = R.drawable.email, onChange = {})
            InputForm(title = "Password", icon = R.drawable.lock, onChange = {})
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary_purple)
                )
            ) {
                Text(text = "Login", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = buildAnnotatedString {
                    append("Don't have an account? ")

                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.primary_purple)
                        )
                    ) {
                        append("Register here")
                    }
                },
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            //RegisterScreen()
                        }
                    )
            )
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())

}