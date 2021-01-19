package com.bilev.recipe.dto;

import com.bilev.recipe.entity.basic.MeasurementType;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
public class RecipeIngredientDto {

    public UUID id;

    @NotNull
    public Integer amount;

    @NotNull
    public MeasurementType measurementType;

    @NotNull
    public UUID ingredientId;
}
