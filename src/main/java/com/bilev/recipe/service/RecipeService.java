package com.bilev.recipe.service;

import com.bilev.recipe.dto.RecipeDto;
import com.bilev.recipe.entity.RecipeEntity;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Dependent
public class RecipeService {

    @Inject
    ConverterService converter;

    public RecipeDto getRecipe(final UUID id) {
        return converter.toDto((RecipeEntity) RecipeEntity.get(id));
    }

    public Collection<RecipeDto> getRecipes(final Date date) {
        return RecipeEntity.find("updatedAt > ?1", date).stream()
                .map(entity -> converter.toDto((RecipeEntity) entity))
                .collect(Collectors.toList());
    }

    @Transactional
    public RecipeDto updateRecipe(final RecipeDto dto) {
        final RecipeEntity entity = converter.toEntity(dto);
        if (entity.id == null) {
            entity.persist();
        }
        return converter.toDto(entity);
    }
}
