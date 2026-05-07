CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS unit_measures (
    id UUID PRIMARY KEY,
    acronym VARCHAR(6) NOT NULL UNIQUE,
    description VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by UUID NOT NULL,
    entity_status VARCHAR(20) NOT NULL
);