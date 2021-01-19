package com.example.recipeapp.repository

import android.util.Log
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.network.RecipeService
import com.example.recipeapp.network.model.RecipeDTOMapper
import com.example.recipeapp.utils.Constants.TAG

/**
 * RecipeRepository_Impl Class that is implementing the [RecipeRepository]
 */
class RecipeRepository_Impl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDTOMapper) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        val results = recipeService.search(token = token, page = page, query = query).recipes
        Log.d(TAG, "search: ${results.size}")
        return mapper.toDomainModel(results)
    }

    override suspend fun searchByID(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.searchByID(token = token, id = id))
    }
}