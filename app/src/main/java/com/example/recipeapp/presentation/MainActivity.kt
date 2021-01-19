package com.example.recipeapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.R
import com.example.recipeapp.network.RecipeService
import com.example.recipeapp.presentation.ui.recipe_list.RecipeListViewModel
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recipeService = Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeService::class.java)

        CoroutineScope(IO).launch {
            val searchById = recipeService.searchByID(
                token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
                id = 583
            )
            Log.d("response", searchById.title ?: "")
        }

    }
}

