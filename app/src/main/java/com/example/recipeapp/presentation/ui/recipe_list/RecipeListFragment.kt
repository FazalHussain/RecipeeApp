package com.example.recipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recipeapp.presentation.components.*
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

                val isShowLoading = viewModel.isShowLoading.value

                val favState = viewModel.favState.value

                Column {
                    SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = viewModel::newSearch,
                            categoryScrollPosition = viewModel.categoryScrollPosition,
                            selectedCategory = selectedCategory,
                            onSelectedCategory = viewModel::onSelectedCategory,
                            onCategoryScrollingStateChanged =
                            viewModel::onCategoryScrollingStateChanged
                    )



                    //PulsingDemo()

                    // Box composable is used to overlap the childrens with each other
                    // The children which is placed at lower would be on top
                    Box(modifier = Modifier.fillMaxSize()) {

                        LazyColumn {
                            itemsIndexed(items = recipes) { index, recipe ->
                                RecipeCard(recipe,
                                        viewModel::changeFavState,
                                        onClick = { }
                                )
                            }
                        }

                        CircularIndeterminateProgressBar(isDisplayed = isShowLoading)
                    }


                }



            }
        }
    }
}