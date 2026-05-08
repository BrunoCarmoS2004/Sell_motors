#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE DATABASE sell_motors_master;
    CREATE DATABASE sell_motors_leads;
    CREATE DATABASE sell_motors_vehicles;
EOSQL
