CREATE TABLE leads (
                       id UUID PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       phone VARCHAR(50) NOT NULL,
                       document VARCHAR(50),
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);