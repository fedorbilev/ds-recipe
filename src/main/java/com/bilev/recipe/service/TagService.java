package com.bilev.recipe.service;

import com.bilev.common.exception.ApplicationException;
import com.bilev.common.exception.ExceptionCode;
import com.bilev.recipe.dto.TagDto;
import com.bilev.recipe.entity.TagEntity;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Dependent
public class TagService {

    @Inject
    ConverterService converter;

    public Collection<TagDto> getTags(final Date date) {
        return TagEntity.find("updatedAt > ?1", date).stream()
                .map(entity -> converter.toDto((TagEntity) entity))
                .collect(Collectors.toList());
    }

    public TagDto getTag(final UUID id) {
        return converter.toDto((TagEntity) TagEntity.get(id));
    }

    @Transactional
    public TagDto updateTag(final TagDto dto) {
        final TagEntity entity = converter.toEntity(dto);
        if (entity.id == null) {
            entity.persist();
        }
        return converter.toDto(entity);
    }

    @Transactional
    public void removeTag(final UUID id) {
        final TagEntity entity = (TagEntity) TagEntity.get(id);
        if (!entity.ingredients.isEmpty() || !entity.recipes.isEmpty()) {
            throw new ApplicationException(ExceptionCode.ILLEGAL_STATE, "Tag has dependencies");
        }
        entity.delete();
    }
}
