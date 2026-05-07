CREATE TABLE user_address
(
    id                UUID         NOT NULL,
    zip_code          VARCHAR(255) NOT NULL,
    street_address    VARCHAR(255) NOT NULL,
    number            INTEGER      NOT NULL,
    neighborhood_type VARCHAR(255) NOT NULL,
    neighborhood      VARCHAR(255) NOT NULL,
    complement        VARCHAR(255),
    state             VARCHAR(255) NOT NULL,
    city              VARCHAR(255) NOT NULL,
    city_ibge         VARCHAR(255) NOT NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    address_of_id     UUID         NOT NULL,
    entity_status     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_address PRIMARY KEY (id)
);

CREATE TABLE users
(
    id              UUID         NOT NULL,
    name            VARCHAR(255) NOT NULL,
    inscription     VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    telephone       VARCHAR(255) NOT NULL,
    cell_phone      VARCHAR(255),
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_acess      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_role       VARCHAR(255) NOT NULL,
    db_status       VARCHAR(255) NOT NULL,
    creation_status VARCHAR(255) NOT NULL,
    entity_status   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_tenants
(
    id           UUID         NOT NULL,
    db_name      VARCHAR(255) NOT NULL,
    db_user_id   UUID         NOT NULL,
    user_name    VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    driver_class VARCHAR(255) NOT NULL,
    db_status    VARCHAR(255) NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_users_tenants PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_inscription UNIQUE (inscription);