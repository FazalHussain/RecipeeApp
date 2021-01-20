package com.example.recipeapp.presentation.ui.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.repository.RecipeRepository
import com.example.recipeapp.utils.Constants.TAG
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Named

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

    init {
        newSearch(query.value)
    }

    fun newSearch(query: String) {
        viewModelScope.launch {
            val result = recipeRepository.search(
                token = authToken,
                page = 1,
                query = query,
            )
            recipes.value = result
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }


}