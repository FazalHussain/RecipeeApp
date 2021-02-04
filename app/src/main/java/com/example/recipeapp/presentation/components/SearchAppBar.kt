package com.example.recipeapp.presentation.components

import android.util.Log
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.recipeapp.presentation.ui.recipe_list.FoodCategory
import com.example.recipeapp.presentation.ui.recipe_list.getAllFoodCategories
import com.example.recipeapp.utils.Constants

@Composable
fun SearchAppBar(
        query: String,
        onQueryChanged: (String) -> Unit,
        onExecuteSearch: () -> Unit,
        categoryScrollPosition: Float,
        selectedCategory: FoodCategory?,
        onSelectedCategory: (String) -> Unit,
        onCategoryScrollingStateChanged: (Float) -> Unit,
        onToogleTheme: () -> Unit
) {
    Surface(
            modifier = Modifier
                    .fillMaxWidth(),
            color = MaterialTheme.colors.surface,
            elevation = 8.dp
    ) {
        Row {
            Column {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                            modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .padding(8.dp),
                            value = query,
                            onValueChange = {
                                Log.d(Constants.TAG, "onCreateView: $it")
                                onQueryChanged(it)
                            },
                            label = {
                                Text(text = "Search")
                            },
                            keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Search
                            ),
                            leadingIcon = {
                                Icon(Icons.Filled.Search)
                            },
                            onImeActionPerformed = { action,
                                                     keyboardController ->
                                if (action == ImeAction.Search) {
                                    onExecuteSearch()
                                    keyboardController?.hideSoftwareKeyboard()
                                }
                            },
                            textStyle = TextStyle(color = MaterialTheme
                                    .colors.onSurface),
                            backgroundColor = MaterialTheme.colors.surface

                    )


                    ConstraintLayout(
                            modifier = Modifier
                                    .align(Alignment.CenterVertically)
                    ) {
                        val menu = createRef()
                        IconButton(
                                onClick = onToogleTheme,
                                modifier = Modifier.constrainAs(menu) {
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                }
                        ) {
                            Icon(Icons.Filled.MoreVert)
                        }
                    }

                }


                val scrollState = rememberScrollState() //remember the scrolling state

                ScrollableRow(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, bottom = 8.dp),
                        scrollState = scrollState
                ) {
                    // update the scroll position in scroll state
                    scrollState.scrollTo(categoryScrollPosition)
                    for (foodCategory in getAllFoodCategories()) {
                        FoodCategoryChip(
                                category = foodCategory.value,
                                isSelected = selectedCategory == foodCategory,
                                onSelectedCategoryChanged = {
                                    onSelectedCategory(it)
                                    // set the category state change based on the scroll state
                                    onCategoryScrollingStateChanged(
                                            scrollState.value
                                    )
                                },
                                onExecuteSearch = {
                                    onExecuteSearch()
                                }
                        )
                    }
                }
            }
        }
    }
}