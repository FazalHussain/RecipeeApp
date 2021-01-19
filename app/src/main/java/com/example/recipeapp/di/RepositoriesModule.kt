package com.example.recipeapp.di

import com.example.recipeapp.network.RecipeService
import com.example.recipeapp.network.model.RecipeDTOMapper
import com.example.recipeapp.repository.RecipeRepository
import com.example.recipeapp.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RepositoriesModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(dtoMapper: RecipeDTOMapper,
                                recipeService: RecipeService): RecipeRepository {
        return RecipeRepository_Impl(recipeService = recipeService, mapper = dtoMapper)
    }
}