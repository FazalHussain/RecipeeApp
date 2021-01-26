package com.example.recipeapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CircularIndeterminateProgressBar(
        isDisplayed: Boolean
) {
    if (isDisplayed) {
        ConstraintLayout(
                modifier = Modifier.fillMaxSize()
        ) {
            // create the ids for composable for constraint layout
            val (progressBar, text) = createRefs()
            // create the imaginary guideline from the top of 30 % of screen height
            val topGuideline = createGuidelineFromTop(0.3f)
            CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.constrainAs(progressBar) { // Assign the constraints
                        top.linkTo(topGuideline)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                    text = "Loading...",
                    style = TextStyle(
                            color = Color.Black,
                            fontSize = TextUnit.Companion.Sp(15)
                    ),
                    modifier = Modifier.constrainAs(text) {
                        top.linkTo(progressBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
        /*Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(50.dp),

                horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                    color = MaterialTheme.colors.primary
            )
        }*/
    }
}