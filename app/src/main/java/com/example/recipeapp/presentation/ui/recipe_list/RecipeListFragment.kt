package com.example.recipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recipeapp.presentation.components.FoodCategoryChip
import com.example.recipeapp.presentation.components.RecipeCard
import com.example.recipeapp.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                // if this value change this composable would be recompose
                val recipes = viewModel.recipes.value

                // remember stores a single object in memory while
                // recomposition it will return the stored value
                //val query = remember { mutableStateOf("chcik") }
                val query = viewModel.query.value

                val selectedCategory = viewModel.selectedCategory.value

                Column {
                    Surface(
                            modifier = Modifier
                                    .fillMaxWidth(),
                            color = Color.White,
                            elevation = 8.dp
                    ) {
                        Row {
                            Column {
                                TextField(
                                        modifier = Modifier
                                                .fillMaxWidth(0.9f)
                                                .padding(8.dp),
                                        value = query,
                                        onValueChange = {
                                            Log.d(TAG, "onCreateView: $it")
                                            viewModel.onQueryChanged(it)
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
                                                viewModel.newSearch()
                                                keyboardController?.hideSoftwareKeyboard()
                                            }
                                        },
                                        textStyle = TextStyle(color = MaterialTheme
                                                .colors.onSurface),
                                        backgroundColor = MaterialTheme.colors.surface

                                )

                                val scrollState = rememberScrollState() //remember the scrolling state

                                ScrollableRow(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 8.dp, bottom = 8.dp),
                                        scrollState = scrollState
                                ) {
                                    // update the scroll position in scroll state
                                    scrollState.scrollTo(viewModel.categoryScrollPosition)
                                    for (foodCategory in getAllFoodCategories()) {
                                        FoodCategoryChip(
                                                category = foodCategory.value,
                                                isSelected = selectedCategory == foodCategory,
                                                onSelectedCategoryChanged = {
                                                    viewModel.onSelectedCategory(it)
                                                    // set the category state change based on the scroll state
                                                    viewModel.onCategoryScrollingStateChanged(
                                                            scrollState.value
                                                    )
                                                },
                                                onExecuteSearch = viewModel::newSearch
                                        )
                                    }
                                }
                            }

                        }
                    }
                    LazyColumn {
                        itemsIndexed(items = recipes) { index, recipe ->
                            RecipeCard(recipe, onClick = {})
                        }
                    }
                }



            }
        }
    }
}