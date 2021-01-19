package com.example.recipeapp.di

import com.example.recipeapp.network.RecipeService
import com.example.recipeapp.network.model.RecipeDTOMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRecipeDtoMapper(): RecipeDTOMapper {
        return RecipeDTOMapper()
    }

    @Singleton
    @Provides
    fun provideRecipeService(): RecipeService {
        return Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeService::class.java)
    }

    @Singleton
    @Named("auth_token")
    @Provides
    fun provideAuthToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }

}