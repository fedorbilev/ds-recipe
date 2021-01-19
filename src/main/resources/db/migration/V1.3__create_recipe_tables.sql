CREATE TABLE ds_recipe.t_recipe
(
    id          UUID,
    modified_at TIMESTAMP NOT NULL,
    modified_by TEXT      NOT NULL,
    obsolete    BOOLEAN   NOT NULL,
    kcal        INTEGER   NOT NULL,
    breakfast   BOOLEAN   NOT NULL,
    lunch       BOOLEAN   NOT NULL,
    dinner      BOOLEAN   NOT NULL,
    snack       BOOLEAN   NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE ds_recipe.t_recipe_translation
(
    translation_id    UUID       NOT NULL,
    language_code     VARCHAR(2) NOT NULL,
    transaction_value TEXT       NOT NULL,

    UNIQUE (translation_id, language_code),
    FOREIGN KEY (translation_id) REFERENCES ds_recipe.t_recipe,
    CHECK (language_code IN ('EN', 'FR', 'DE', 'ES', 'JA', 'RU', 'IT'))
);

CREATE INDEX t_recipe_translation_id_index
    ON ds_recipe.t_recipe_translation (translation_id);

CREATE TABLE ds_recipe.t_recipe_ingredient
(
    id               UUID,
    recipe_id        UUID      NOT NULL,
    ingredient_id    UUID      NOT NULL,
    amount           INTEGER   NOT NULL,
    measurement_type TEXT      NOT NULL,
    modified_at      TIMESTAMP NOT NULL,
    modified_by      TEXT      NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (recipe_id) REFERENCES ds_recipe.t_recipe,
    FOREIGN KEY (ingredient_id) REFERENCES ds_recipe.t_ingredient,
    CHECK (measurement_type IN ('PIECE', 'GRAM', 'MILLILITER'))
);

CREATE INDEX t_recipe_ingredient_recipe_id_index
    ON ds_recipe.t_recipe_ingredient (recipe_id);

CREATE TABLE ds_recipe.t_recipe_tag
(
    recipe_id UUID NOT NULL,
    tag_id    UUID NOT NULL,

    FOREIGN KEY (recipe_id) REFERENCES ds_recipe.t_recipe,
    FOREIGN KEY (tag_id) REFERENCES ds_recipe.t_tag
);