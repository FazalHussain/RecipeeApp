package com.example.recipeapp.network

import com.example.recipeapp.network.model.RecipeDTO
import com.example.recipeapp.network.responses.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeService {

    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ) : RecipeSearchResponse

    @GET("get")
    suspend fun searchByID(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
    ) : RecipeDTO
}