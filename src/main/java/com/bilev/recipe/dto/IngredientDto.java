package com.bilev.recipe.dto;

import com.bilev.recipe.entity.basic.LanguageCode;
import lombok.Builder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Schema(description="Dto for ingredient")
@Builder
public class IngredientDto {

    @Schema(description = "Ingredient identifier (null for creation)", nullable = true,
            example = "f79c0012-bf63-43b0-9d11-da0549b13a7e")
    public UUID id;

    @Schema(description = "Tag identifier", required = true, example = "f79c0012-bf63-43b0-9d11-da0549b13a7e")
    @NotNull
    public UUID tagId;

    @Schema(description = "Entity modified at time", readOnly = true, example = "")
    public Date modifiedAt;

    @Schema(description = "Who modified entity", readOnly = true, example = "")
    public Date modifiedBy;

    @Schema(description = "Map of localized name")
    @NotNull
    @Builder.Default
    public Map<LanguageCode, String> translations = new HashMap<>();
}
