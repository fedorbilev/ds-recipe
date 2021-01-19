package com.bilev.recipe.message;

import io.quarkus.qute.i18n.Message;
import io.quarkus.qute.i18n.MessageBundle;

import java.util.UUID;

@MessageBundle
public interface ExceptionMessage {

    @Message("Tag with id: {id} not found")
    String tagNotFound(final UUID id);

    @Message("Ingredient with id: {id} not found")
    String ingredientNotFound(final UUID id);

    @Message("Step with id: {id} not found")
    String stepNotFound(final UUID id);

    @Message("Recipe with id: {id} not found")
    String recipeNotFound(final UUID id);

    @Message("Recipe ingredient with id: {id} not found")
    String recipeIngredientNotFound(final UUID id);
}
