package com.bilev.recipe.dto;

import com.bilev.recipe.entity.basic.LanguageCode;
import lombok.Builder;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Schema(description="Dto for recipe")
@Builder
public class RecipeDto {

    @Schema(description = "Recipe identifier (null for creation)", nullable = true,
            example = "f79c0012-bf63-43b0-9d11-da0549b13a7e")
    public UUID id;

    @Schema(description = "Recipe kcal amount", required = true, example = "50")
    @NotNull
    public Integer kcal;

    @Schema(description = "Is recipe obsolete", required = true, example = "false")
    @NotNull
    public Boolean obsolete;

    @Schema(description = "Is recipe applied for breakfast", required = true, example = "false")
    @NotNull
    public Boolean breakfast;

    @Schema(description = "Is recipe applied for lunch", required = true, example = "false")
    @NotNull
    public Boolean lunch;

    @Schema(description = "Is recipe applied for dinner", required = true, example = "false")
    @NotNull
    public Boolean dinner;

    @Schema(description = "Is recipe applied for snack", required = true, example = "false")
    @NotNull
    public Boolean snack;

    @Schema(description = "Entity modified at time", readOnly = true, example = "")
    public Date modifiedAt;

    @Schema(description = "Who modified entity", readOnly = true, example = "")
    public Date modifiedBy;

    @Schema(description = "List of steps", required = true, implementation = StepDto.class,
            type = SchemaType.ARRAY, minItems = 1)
    @NotNull
    @Size(min = 1)
    @Builder.Default
    public Collection<StepDto> steps = new ArrayList<>();

    @Schema(description = "List of ingredients", required = true, implementation = RecipeIngredientDto.class,
            type = SchemaType.ARRAY, minItems = 1)
    @NotNull
    @Size(min = 1)
    @Builder.Default
    public Collection<RecipeIngredientDto> ingredients = new ArrayList<>();

    @Schema(description = "List of tags", required = true,implementation = UUID.class,
            type = SchemaType.ARRAY, minItems = 1)
    @NotNull
    @Size(min = 1)
    @Builder.Default
    public Collection<UUID> tags = new ArrayList<>();

    @Schema(description = "Map of localized name")
    @NotNull
    @Builder.Default
    public Map<LanguageCode, String> translations = new HashMap<>();
}
