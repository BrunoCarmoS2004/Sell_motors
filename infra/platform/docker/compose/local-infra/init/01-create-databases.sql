SELECT 'CREATE DATABASE sell_motors_vehicles'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'sell_motors_vehicles')
\gexec

SELECT 'CREATE DATABASE sell_motors_leads'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'sell_motors_leads')
\gexec
