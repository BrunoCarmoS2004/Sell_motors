@echo off
echo Iniciando containers...
docker compose -f docker-compose.yml up -d
pause