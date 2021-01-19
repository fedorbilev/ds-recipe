package com.bilev.recipe.dto;

import com.bilev.recipe.entity.basic.LanguageCode;
import lombok.Builder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Schema(description="Dto for step in recipe")
@Builder
public class StepDto {

    @Schema(description = "Step identifier (null for creation)", nullable = true,
            example = "f79c0012-bf63-43b0-9d11-da0549b13a7e")
    public UUID id;

    @Schema(description = "Step order number", required = true, example = "2")
    @NotNull
    public Integer stepNumber;

    @Schema(description = "Step requires time (in min)", nullable = true, example = "5")
    public Integer time;

    @Schema(description = "Map of localized name")
    @NotNull
    @Builder.Default
    public Map<LanguageCode, String> translations = new HashMap<>();

}
