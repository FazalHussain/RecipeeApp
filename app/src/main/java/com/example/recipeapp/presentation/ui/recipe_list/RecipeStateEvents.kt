package com.example.recipeapp.presentation.ui.recipe_list

sealed class RecipeStateEvents {
    object NewSearchEvent : RecipeStateEvents()
    object NextPageEvent : RecipeStateEvents()
}