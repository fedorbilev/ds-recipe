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
@Table(schema = AppConstants.DATA_MODEL_SCHEMA_NAME, name = "t_tag")
@EqualsAndHashCode(callSuper = true)
public class TagEntity extends BaseEntity {

    @OneToMany(mappedBy = "tag")
    public Collection<IngredientEntity> ingredients = new ArrayList<>();

    @ManyToMany(mappedBy = "tags")
    public Collection<RecipeEntity> recipes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(schema = AppConstants.DATA_MODEL_SCHEMA_NAME, name = "t_tag_translation",
            joinColumns = @JoinColumn(name = "translation_id"))
    @MapKeyColumn(name = "language_code")
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "transaction_value")
    public Map<LanguageCode, String> translations = new HashMap<>();

    public static TagEntity get(final UUID id) {
        return (TagEntity) Optional.ofNullable(id).flatMap(TagEntity::findByIdOptional)
                .orElseThrow(() -> new ApplicationException(ExceptionCode.NOT_FOUND,
                        MessageBundles.get(ExceptionMessage.class).tagNotFound(id)));
    }
}
