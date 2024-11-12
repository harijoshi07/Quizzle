package com.example.quizgame.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.quizgame.R

@Composable
fun AlertDialogComponent(
    dialogState: MutableState<Boolean>,
    navigateToHome: () -> Unit
) {

    if (dialogState.value) {

        AlertDialog(
            title = {
                Text(text = "Are you sure you want to exit?")
            },
            text = {
                Text(text = "Your progress will not be saved and the quiz will not be recorded")
            },
            containerColor = colorResource(id = R.color.white),

            onDismissRequest = { dialogState.value = false },
            confirmButton = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { dialogState.value = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.primary_purple)
                        )
                    ) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            dialogState.value = false // Close dialog before navigation
                            navigateToHome()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.primary_purple)
                        )
                    ) {
                        Text(text = "Yes, exit")
                    }
                }
            }
        )
    }
}