package com.example.recipeapp.repository

import com.example.recipeapp.domain.model.Recipe

/**
 * Recipe Repository Interface.
 *
 * Note: Access the Domain Model only. No DTO NO Entity object
 */
interface RecipeRepository {

    suspend fun search(token: String, page: Int, query: String) : List<Recipe>

    suspend fun searchByID(token: String, id: Int) : Recipe
}