package com.bilev.recipe.service;

import com.bilev.recipe.dto.IngredientDto;
import com.bilev.recipe.entity.IngredientEntity;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Dependent
public class IngredientService {

    @Inject
    ConverterService converter;

    public Collection<IngredientDto> getIngredients(final Date date) {
        return IngredientEntity.find("updatedAt > ?1", date).stream()
                .map(entity -> converter.toDto((IngredientEntity) entity))
                .collect(Collectors.toList());
    }

    public IngredientDto getIngredient(final UUID id) {
        return converter.toDto((IngredientEntity) IngredientEntity.get(id));
    }

    @Transactional
    public IngredientDto updateIngredient(final IngredientDto dto) {
        final IngredientEntity entity = converter.toEntity(dto);
        if (entity.id == null) {
            entity.persist();
        }
        return converter.toDto(entity);
    }

    @Transactional
    public void removeIngredient(final UUID id) {
        final IngredientEntity entity = (IngredientEntity) IngredientEntity.get(id);
        if (!entity.recipeIngredients.isEmpty()) {
            //throw new ApplicationException("Ingredient has dependencies");
        }
        entity.delete();
    }
}
