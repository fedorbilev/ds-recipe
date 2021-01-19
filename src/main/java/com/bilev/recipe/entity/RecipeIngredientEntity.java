package com.bilev.recipe.entity;

import com.bilev.common.config.AppConstants;
import com.bilev.common.entity.BaseEntity;
import com.bilev.recipe.entity.basic.MeasurementType;
import com.bilev.common.exception.ApplicationException;
import com.bilev.common.exception.ExceptionCode;
import com.bilev.recipe.message.ExceptionMessage;
import io.quarkus.qute.i18n.MessageBundles;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(schema = AppConstants.DATA_MODEL_SCHEMA_NAME, name = "t_recipe_ingredient")
@EqualsAndHashCode(callSuper = true)
public class RecipeIngredientEntity extends BaseEntity {

    @Column(name = "amount", nullable = false)
    public Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "measurement_type", nullable = false)
    public MeasurementType measurementType;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    public IngredientEntity ingredient;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    public RecipeEntity recipe;

    public static RecipeIngredientEntity get(final UUID id) {
        return (RecipeIngredientEntity) Optional.ofNullable(id).flatMap(RecipeIngredientEntity::findByIdOptional)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.NOT_FOUND,
                        MessageBundles.get(ExceptionMessage.class).recipeIngredientNotFound(id)));
    }
}
