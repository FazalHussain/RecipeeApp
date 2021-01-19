package com.example.recipeapp.network.model

import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.utils.DomainMapper

class RecipeDTOMapper : DomainMapper<RecipeDTO, Recipe> {
    override fun mapToDomainModel(model: RecipeDTO): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            publisher = model.publisher,
            featuredImage = model.featuredImage,
            rating = model.rating,
            sourceUrl = model.sourceUrl,
            description = model.description,
            cookingInstructions = model.cookingInstructions,
            ingredients = model.ingredients?: listOf(),
            dateAdded = model.dateAdded,
            dateUpdated = model.dateUpdated
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDTO {
        return RecipeDTO(
            pk = domainModel.id,
            title = domainModel.title,
            publisher = domainModel.publisher,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            cookingInstructions = domainModel.cookingInstructions,
            ingredients = domainModel.ingredients,
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated
        )
    }

    fun toDomainModel(initials: List<RecipeDTO>) : List<Recipe> {
        return initials.map { mapToDomainModel(it) }
    }

    fun fromDomainModel(initials: List<Recipe>) : List<RecipeDTO> {
        return initials.map { mapFromDomainModel(it) }
    }

}