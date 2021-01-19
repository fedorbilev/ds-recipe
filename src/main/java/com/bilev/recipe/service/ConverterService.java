package com.bilev.recipe.service;

import com.bilev.recipe.dto.IngredientDto;
import com.bilev.recipe.dto.RecipeDto;
import com.bilev.recipe.dto.RecipeIngredientDto;
import com.bilev.recipe.dto.StepDto;
import com.bilev.recipe.dto.TagDto;
import com.bilev.recipe.entity.IngredientEntity;
import com.bilev.recipe.entity.RecipeEntity;
import com.bilev.recipe.entity.RecipeIngredientEntity;
import com.bilev.recipe.entity.StepEntity;
import com.bilev.recipe.entity.TagEntity;
import com.sun.istack.NotNull;

import javax.enterprise.context.Dependent;

@Dependent
public class ConverterService {

    public TagEntity toEntity(@NotNull final TagDto dto) {
        final TagEntity entity = dto.id != null
                ? TagEntity.get(dto.id)
                : new TagEntity();
        entity.translations = dto.translations;
        return entity;
    }

    public TagDto toDto(@NotNull final TagEntity entity) {
        return TagDto.builder()
                .id(entity.id)
                .modifiedAt(entity.modifiedAt)
                .modifiedBy(entity.modifiedBy)
                .translations(entity.translations).build();
    }

    public IngredientEntity toEntity(@NotNull final IngredientDto dto) {
        final IngredientEntity entity = dto.id != null
                ? IngredientEntity.get(dto.id)
                : new IngredientEntity();
        entity.tag = TagEntity.get(dto.tagId);
        entity.translations = dto.translations;
        return entity;
    }

    public IngredientDto toDto(@NotNull final IngredientEntity entity) {
        return IngredientDto.builder()
                .id(entity.id)
                .tagId(entity.tag.id)
                .modifiedAt(entity.modifiedAt)
                .modifiedBy(entity.modifiedBy)
                .translations(entity.translations).build();
    }

    public RecipeEntity toEntity(final RecipeDto dto) {
        final RecipeEntity entity = dto.id != null
                ? RecipeEntity.get(dto.id)
                : new RecipeEntity();

        entity.kcal = dto.kcal;
        entity.obsolete = dto.obsolete;
        entity.translations = dto.translations;
        entity.breakfast = dto.breakfast;
        entity.lunch = dto.lunch;
        entity.dinner = dto.dinner;
        entity.snack = dto.snack;
        dto.tags.forEach(tagId -> entity.tags.add(TagEntity.get(tagId)));
        dto.ingredients.forEach(ingredientDto -> {
            final RecipeIngredientEntity ingredientEntity = toEntity(ingredientDto);
            ingredientEntity.recipe = entity;
            entity.ingredients.add(ingredientEntity);
        });
        dto.steps.forEach(stepDto -> {
            final StepEntity stepEntity = toEntity(stepDto);
            stepEntity.recipe = entity;
            entity.steps.add(stepEntity);
        });
        return entity;
    }

    public RecipeDto toDto(final RecipeEntity entity) {
        final RecipeDto dto = RecipeDto.builder()
                .id(entity.id)
                .kcal(entity.kcal)
                .obsolete(entity.obsolete)
                .breakfast(entity.breakfast)
                .lunch(entity.lunch)
                .dinner(entity.dinner)
                .snack(entity.snack)
                .modifiedAt(entity.modifiedAt)
                .modifiedBy(entity.modifiedBy)
                .translations(entity.translations).build();
        entity.steps.forEach(step -> dto.steps.add(toDto(step)));
        entity.ingredients.forEach(ingredient -> dto.ingredients.add(toDto(ingredient)));
        entity.tags.forEach(tag -> dto.tags.add(tag.id));
        return dto;
    }

    private StepEntity toEntity(final StepDto dto) {
        final StepEntity entity = dto.id != null
                ? StepEntity.get(dto.id)
                : new StepEntity();
        entity.stepNumber = dto.stepNumber;
        entity.time = dto.time;
        entity.translations = dto.translations;
        return entity;
    }

    private StepDto toDto(final StepEntity entity) {
        return StepDto.builder()
                .id(entity.id)
                .stepNumber(entity.stepNumber)
                .time(entity.time)
                .translations(entity.translations).build();
    }

    private RecipeIngredientEntity toEntity(final RecipeIngredientDto dto) {
        final RecipeIngredientEntity entity = dto.id != null
                ? RecipeIngredientEntity.get(dto.id)
                : new RecipeIngredientEntity();
        entity.amount = dto.amount;
        entity.measurementType = dto.measurementType;
        entity.ingredient = IngredientEntity.get(dto.ingredientId);
        return entity;
    }

    private RecipeIngredientDto toDto(final RecipeIngredientEntity entity) {
        return RecipeIngredientDto.builder()
                .id(entity.id)
                .amount(entity.amount)
                .measurementType(entity.measurementType)
                .ingredientId(entity.ingredient.id).build();
    }

}
