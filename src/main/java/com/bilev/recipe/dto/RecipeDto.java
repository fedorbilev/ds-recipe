package com.bilev.recipe.dto;

import com.bilev.recipe.entity.basic.LanguageCode;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Builder
public class RecipeDto {

    public UUID id;

    @NotNull
    public Integer kcal;

    @NotNull
    public Boolean obsolete;

    @NotNull
    public Boolean breakfast;

    @NotNull
    public Boolean lunch;

    @NotNull
    public Boolean dinner;

    @NotNull
    public Boolean snack;

    public Date modifiedAt;

    public Date modifiedBy;

    @NotNull
    @Size(min = 1)
    @Builder.Default
    public Collection<StepDto> steps = new ArrayList<>();

    @NotNull
    @Size(min = 1)
    @Builder.Default
    public Collection<RecipeIngredientDto> ingredients = new ArrayList<>();

    @NotNull
    @Size(min = 1)
    @Builder.Default
    public Collection<UUID> tags = new ArrayList<>();

    @NotNull
    @Builder.Default
    public Map<LanguageCode, String> translations = new HashMap<>();
}
