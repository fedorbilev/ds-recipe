package com.bilev.recipe.dto;

import com.bilev.recipe.entity.basic.LanguageCode;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Builder
public class TagDto {

    public UUID id;

    public Date modifiedAt;

    public Date modifiedBy;

    @NotNull
    @Builder.Default
    public Map<LanguageCode, String> translations = new HashMap<>();
}
