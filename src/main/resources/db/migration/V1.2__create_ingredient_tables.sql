CREATE TABLE ds_recipe.t_ingredient
(
    id          UUID,
    tag_id      UUID      NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    modified_by TEXT      NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (tag_id) REFERENCES ds_recipe.t_tag
);

CREATE TABLE ds_recipe.t_ingredient_translation
(
    translation_id    UUID       NOT NULL,
    language_code     VARCHAR(2) NOT NULL,
    transaction_value TEXT       NOT NULL,

    UNIQUE (translation_id, language_code),
    FOREIGN KEY (translation_id) REFERENCES ds_recipe.t_ingredient,
    CHECK (language_code IN ('EN', 'FR', 'DE', 'ES', 'JA', 'RU', 'IT'))
);

CREATE INDEX t_ingredient_translation_id_index
    ON ds_recipe.t_ingredient_translation (translation_id);