DROP TABLE article IF EXISTS;
DROP TABLE category IF EXISTS;
DROP TABLE bloguser IF EXISTS;

CREATE TABLE bloguser
(
    id          bigserial                   NOT NULL PRIMARY KEY,               -- user id
    name        varchar(50)                 NOT NULL,                           -- user name
    email       varchar(60)                 NOT NULL UNIQUE,                    -- user email
    password    varchar(256)                NOT NULL,                           -- user password
    is_admin    boolean                     NOT NULL DEFAULT false,             -- is this user admin ?
    is_active   boolean                     NOT NULL DEFAULT false,             -- is this user activated?
    created_at  TIMESTAMP WITH TIME ZONE    NOT NULL DEFAULT CURRENT_TIMESTAMP, -- create date
    updated_at  TIMESTAMP WITH TIME ZONE                                        -- last updated date
);


CREATE TABLE category
(
    id          bigserial                   NOT NULL PRIMARY KEY,
    name        varchar(32)                 NOT NULL
);

CREATE TABLE article
(
    id          bigserial                  NOT NULL PRIMARY KEY,
    title       varchar(128)                NOT NULL,
    content     text                        NOT NULL,
    category_id bigint,
    author_id   bigint,
    created_at  TIMESTAMP WITH TIME ZONE    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_category_id
        FOREIGN KEY(category_id)
            REFERENCES "category"(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_author_id
        FOREIGN KEY(author_id)
            REFERENCES "bloguser"(id)
            ON DELETE CASCADE
)
