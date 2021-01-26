package com.example.recipeapp.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.recipeapp.presentation.components.HeartAnimationDefinition.HeartStates
import com.example.recipeapp.presentation.components.HeartAnimationDefinition.HeartStates.IDLE


data class Recipe(
    var id: Int? = null,
    val title: String? = null,
    val publisher: String? = null,
    val featuredImage: String? = null,
    val rating: Int? = null,
    val sourceUrl: String? = null,
    val description: String? = null,
    val cookingInstructions: String? = null,
    val ingredients: List<String> = listOf(),
    val dateAdded: String? = null,
    val dateUpdated: String? = null,
    val isFav: MutableState<HeartStates> = mutableStateOf(IDLE)
)