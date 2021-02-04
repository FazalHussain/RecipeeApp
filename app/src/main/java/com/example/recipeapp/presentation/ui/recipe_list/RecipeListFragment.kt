package com.example.recipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MobileFriendly
import androidx.compose.material.icons.filled.MobileScreenShare
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.example.recipeapp.presentation.BaseApplication
import com.example.recipeapp.presentation.components.*
import com.example.recipeapp.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    @Inject
    lateinit var application: BaseApplication

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

                AppTheme(
                        darkTheme = application.isDark.value
                ) {
                    // if this value change this composable would be recompose
                    val recipes = viewModel.recipes.value

                    // remember stores a single object in memory while
                    // recomposition it will return the stored value
                    //val query = remember { mutableStateOf("chcik") }
                    val query = viewModel.query.value

                    val page = viewModel.page.value

                    val selectedCategory = viewModel.selectedCategory.value

                    val isShowLoading = viewModel.isShowLoading.value

                    Scaffold(
                            topBar = {
                                SearchAppBar(
                                        query = query,
                                        onQueryChanged = viewModel::onQueryChanged,
                                        onExecuteSearch = viewModel::newSearch,
                                        categoryScrollPosition = viewModel.categoryScrollPosition,
                                        selectedCategory = selectedCategory,
                                        onSelectedCategory = viewModel::onSelectedCategory,
                                        onCategoryScrollingStateChanged =
                                        viewModel::onCategoryScrollingStateChanged,
                                        onToogleTheme = {
                                            application.onThemeChange()
                                        }
                                )
                            }
                    ) {
                        // Box composable is used to overlap the childrens with each other
                        // The children which is placed at lower would be on top
                        Box(modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colors.background)) {
                            if (isShowLoading && recipes.isEmpty()) {
                                LoadingRecipeShimmer(250.dp)
                            } else {
                                LazyColumn {
                                    itemsIndexed(items = recipes) { index, recipe ->
                                        viewModel.onScrollPositionChanges(index)
                                        // Check if scroll position reached to the bottom of the list
                                        if ((index + 1) >= (page * PAGE_SIZE) &&
                                                !isShowLoading) {
                                            viewModel.nextPage()
                                        }
                                        RecipeCard(recipe,
                                                viewModel::changeFavState,
                                                onClick = { }
                                        )
                                    }
                                }
                            }
                            CircularIndeterminateProgressBar(isDisplayed = isShowLoading)
                        }
                    }
                }
            }
        }
    }
}