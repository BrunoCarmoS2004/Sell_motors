-- Habilita a extensão de UUID caso queira gerar IDs automaticamente (opcional)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
                                     id UUID NOT NULL,
                                     name VARCHAR(255) NOT NULL,
    inscription VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    telephone VARCHAR(50) NOT NULL,
    cell_phone VARCHAR(50),
    created_at TIMESTAMP NOT NULL,
    last_acess TIMESTAMP NOT NULL,
    user_role VARCHAR(50) NOT NULL,
    db_status VARCHAR(50) NOT NULL,
    creation_status VARCHAR(50) NOT NULL,
    entity_status VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS user_address (
                                            id UUID NOT NULL,
                                            zip_code VARCHAR(20) NOT NULL,
    street_address VARCHAR(255) NOT NULL,
    number INT NOT NULL,
    neighborhood_type VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    complement VARCHAR(255),
    state VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    city_ibge VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    address_of_id UUID NOT NULL,
    entity_status VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
    );
-- Índices no Postgres são criados separadamente
CREATE INDEX idx_address_owner ON user_address (address_of_id);

CREATE TABLE IF NOT EXISTS users_tenants (
                                             id UUID NOT NULL,
                                             db_name VARCHAR(255) NOT NULL,
    db_user_id UUID NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    driver_class VARCHAR(255) NOT NULL,
    db_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
    );
CREATE INDEX idx_tenant_user ON users_tenants (db_user_id);