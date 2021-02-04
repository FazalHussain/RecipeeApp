package com.example.recipeapp.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.components.HeartAnimationDefinition
import com.example.recipeapp.presentation.components.HeartAnimationDefinition.HeartStates.*
import com.example.recipeapp.repository.RecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.FieldPosition
import javax.inject.Named

const val PAGE_SIZE = 30

/**
 * Recipe List View Model is a viewmodel which is life ycler aware
 *
 * [MutableState] is a compose data structure which is used to emit value to the composable.
 * If the value is changed it will recompose the composable where this value is being used.
 */
class RecipeListViewModel
@ViewModelInject
constructor(
    private val recipeRepository: RecipeRepository,
    private @Named("auth_token") val authToken: String
) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())

    val query = mutableStateOf("")

    // Food Category would be null if user search from textfield
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    var categoryScrollPosition: Float = 0f

    val isShowLoading = mutableStateOf(false)

    val page = mutableStateOf(1)

    var scrollingPosition = 0

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            isShowLoading.value = true

            resetSearchState()

            delay(2000)

            val result = recipeRepository.search(
                token = authToken,
                page = 1,
                query = query.value,
            )
            recipes.value = result

            isShowLoading.value = false
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            // Prevent recomposition due to recomposition too quickly
            if ((scrollingPosition + 1) >= page.value * PAGE_SIZE) {
                isShowLoading.value = true
                incrementPage()

                delay(1000)

                if (page.value > 1) {
                    val results = recipeRepository.search(
                            token = authToken,
                            page = page.value,
                            query = query.value
                    )
                    appendRecipe(results)
                }
                isShowLoading.value = false
            }
        }
    }

    fun onScrollPositionChanges(scrollPosition: Int) {
        scrollingPosition = scrollPosition
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

    private fun appendRecipe(recipes: List<Recipe>) {
        val current = ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value = current
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onScrollPositionChanges(0)
        if (selectedCategory.value?.value != query.value) clearSelectedCategory()
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategory(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onCategoryScrollingStateChanged(position: Float) {
        categoryScrollPosition = position
    }

    fun changeFavState(recipe: Recipe) {
        recipe.isFav.value = if (recipe.isFav.value == IDLE) ACTIVE else IDLE
    }


}