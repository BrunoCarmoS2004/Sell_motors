CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE vehicle (
     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
     marca VARCHAR(120) NOT NULL,
     modelo VARCHAR(120) NOT NULL,
     versao VARCHAR(120) NOT NULL,
     ano_modelo INTEGER NOT NULL,
     ano_fabricacao INTEGER NOT NULL,
     preco NUMERIC(19, 2) NOT NULL,
     quilometragem INTEGER NOT NULL,
     tipo_combustivel VARCHAR(30) NOT NULL,
     tipo_transmissao VARCHAR(30) NOT NULL,
     chassi VARCHAR(17) NOT NULL UNIQUE,
     placa VARCHAR(10) NOT NULL UNIQUE,
     status_veiculo VARCHAR(30) NOT NULL,
     entity_status VARCHAR(30),
     deleted_at TIMESTAMP WITHOUT TIME ZONE,
     created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Comentários para auxiliar na manutenção do banco
COMMENT ON COLUMN vehicle.status_veiculo IS 'Armazena o enum VehicleStatus como String';
COMMENT ON COLUMN vehicle.entity_status IS 'Armazena o enum EntityStatus como String (ATIVO, INATIVO, etc)';