package com.bilev.recipe.dto;

import com.bilev.recipe.entity.basic.LanguageCode;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Builder
public class StepDto {

    public UUID id;

    @NotNull
    public Integer stepNumber;

    @NotNull
    public Integer time;

    @NotNull
    @Builder.Default
    public Map<LanguageCode, String> translations = new HashMap<>();

}
