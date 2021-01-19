package com.bilev.recipe.entity;

import com.bilev.common.config.AppConstants;
import com.bilev.common.entity.BaseEntity;
import com.bilev.recipe.entity.basic.LanguageCode;
import com.bilev.common.exception.ApplicationException;
import com.bilev.common.exception.ExceptionCode;
import com.bilev.recipe.message.ExceptionMessage;
import io.quarkus.qute.i18n.MessageBundles;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(schema = AppConstants.DATA_MODEL_SCHEMA_NAME, name = "t_recipe")
@EqualsAndHashCode(callSuper = true)
public class RecipeEntity extends BaseEntity {

    @Column(name = "kcal", nullable = false)
    public Integer kcal;

    @Column(name = "obsolete", nullable = false)
    public Boolean obsolete = false;

    @Column(name = "breakfast", nullable = false)
    public Boolean breakfast = false;

    @Column(name = "lunch", nullable = false)
    public Boolean lunch = false;

    @Column(name = "dinner", nullable = false)
    public Boolean dinner = false;

    @Column(name = "snack", nullable = false)
    public Boolean snack = false;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<StepEntity> steps = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<RecipeIngredientEntity> ingredients = new ArrayList<>();

    @ManyToMany
    @JoinTable(schema = AppConstants.DATA_MODEL_SCHEMA_NAME, name = "t_recipe_tag",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    public Collection<TagEntity> tags = new ArrayList<>();

    @ElementCollection
    @CollectionTable(schema = AppConstants.DATA_MODEL_SCHEMA_NAME, name = "t_recipe_translation",
            joinColumns = @JoinColumn(name = "translation_id"))
    @MapKeyColumn(name = "language_code")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "transaction_value")
    public Map<LanguageCode, String> translations = new HashMap<>();

    public static RecipeEntity get(final UUID id) {
        return (RecipeEntity) Optional.ofNullable(id).flatMap(RecipeEntity::findByIdOptional)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.NOT_FOUND,
                        MessageBundles.get(ExceptionMessage.class).recipeNotFound(id)));
    }
}
