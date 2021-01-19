CREATE TABLE ds_recipe.t_step
(
    id          UUID,
    time        INTEGER,
    step_number INTEGER   NOT NULL,
    recipe_id   UUID      NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    modified_by TEXT      NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (id, step_number),
    FOREIGN KEY (recipe_id) REFERENCES ds_recipe.t_recipe
);

CREATE INDEX t_step_recipe_id_index
    ON ds_recipe.t_step (recipe_id);

CREATE TABLE ds_recipe.t_step_translation
(
    translation_id    UUID       NOT NULL,
    language_code     VARCHAR(2) NOT NULL,
    transaction_value TEXT       NOT NULL,

    UNIQUE (translation_id, language_code),
    FOREIGN KEY (translation_id) REFERENCES ds_recipe.t_step,
    CHECK (language_code IN ('EN', 'FR', 'DE', 'ES', 'JA', 'RU', 'IT'))
);

CREATE INDEX t_step_translation_id_index
    ON ds_recipe.t_step_translation (translation_id);
