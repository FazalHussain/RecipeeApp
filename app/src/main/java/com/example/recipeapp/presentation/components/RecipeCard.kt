package com.example.recipeapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.utils.DEFAULT_RECIPE_IMAGE
import com.example.recipeapp.utils.loadPicture

@Composable
fun RecipeCard(
        recipe: Recipe,
        onFavStateChanged: (Recipe) -> Unit,
        onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(
            bottom = 6.dp,
            top = 6.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Column{
            recipe.featuredImage?.let { url ->
                Box(
                        modifier = Modifier
                                .fillMaxWidth()
                                .preferredHeight(225.dp)
                ) {
                    val image = loadPicture(url = url,
                            defaultDrawable = DEFAULT_RECIPE_IMAGE).value
                    image?.let { img ->
                        Image(bitmap = img.asImageBitmap(),
                                modifier = Modifier
                                        .fillMaxSize(),
                                contentScale = ContentScale.Crop
                        )
                    }

                    AnimatedHeartButton(
                            modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.BottomEnd),
                            buttonState = recipe.isFav,
                            onToggle = {
                                onFavStateChanged(recipe)
                            }
                    )

                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
            ) {
                recipe.title?.let { title ->
                    Text(text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f) // title would take 85% of width
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h5
                    )
                    Text(text = recipe.rating.toString(),
                        modifier = Modifier
                            .fillMaxWidth() // rating would take 15% of width
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}










