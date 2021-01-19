package com.bilev.recipe.dto;

import com.bilev.recipe.entity.basic.MeasurementType;
import lombok.Builder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Schema(description="Dto for ingredient in recipe")
@Builder
public class RecipeIngredientDto {

    @Schema(description = "RecipeIngredient identifier (null for creation)", nullable = true,
            example = "f79c0012-bf63-43b0-9d11-da0549b13a7e")
    public UUID id;

    @Schema(description = "Amount of ingredient", required = true, example = "2")
    @NotNull
    public Integer amount;

    @Schema(description = "Measurement type", required = true, example = "GRAM")
    @NotNull
    public MeasurementType measurementType;

    @Schema(description = "Ingredient identifier", required = true, example = "f79c0012-bf63-43b0-9d11-da0549b13a7e")
    @NotNull
    public UUID ingredientId;
}
