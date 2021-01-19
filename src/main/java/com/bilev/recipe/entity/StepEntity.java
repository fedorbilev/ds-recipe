package com.bilev.recipe.entity;

import com.bilev.common.config.AppConstants;
import com.bilev.common.entity.BaseEntity;
import com.bilev.recipe.entity.basic.LanguageCode;
import com.bilev.common.exception.ApplicationException;
import com.bilev.common.exception.ExceptionCode;
import com.bilev.recipe.message.ExceptionMessage;
import io.quarkus.qute.i18n.MessageBundles;
import lombok.EqualsAndHashCode;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(schema = AppConstants.DATA_MODEL_SCHEMA_NAME, name = "t_step")
@EqualsAndHashCode(callSuper = true)
public class StepEntity extends BaseEntity {

    @Column(name = "time")
    public Integer time;

    @Column(name = "step_number", nullable = false)
    public Integer stepNumber;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    public RecipeEntity recipe;

    @ElementCollection
    @CollectionTable(schema = AppConstants.DATA_MODEL_SCHEMA_NAME, name = "t_step_translation",
            joinColumns = @JoinColumn(name = "translation_id"))
    @MapKeyColumn(name = "language_code")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "transaction_value")
    public Map<LanguageCode, String> translations = new HashMap<>();

    public static StepEntity get(final UUID id) {
        return (StepEntity) Optional.ofNullable(id).flatMap(StepEntity::findByIdOptional)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.NOT_FOUND,
                        MessageBundles.get(ExceptionMessage.class).stepNotFound(id)));
    }
}
