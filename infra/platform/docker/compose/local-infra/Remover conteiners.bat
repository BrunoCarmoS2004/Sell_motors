@echo off
echo Parando e removendo containers e volumes...
docker compose -f docker-compose.yml down -v
pause