.PHONY: help up down build logs logs-backend logs-frontend logs-db logs-kafka restart clean

help:
	@echo "Credit Query Service - Comandos Docker"
	@echo ""
	@echo "Comandos disponíveis:"
	@echo "  make up       - Inicia todos os containers (build + up)"
	@echo "  make down     - Para e remove todos os containers"
	@echo "  make build    - Faz build das imagens sem iniciar"
	@echo "  make logs     - Mostra logs de todos os serviços"
	@echo "  make logs-backend  - Mostra logs do backend"
	@echo "  make logs-frontend - Mostra logs do frontend"
	@echo "  make logs-db       - Mostra logs do banco de dados"
	@echo "  make logs-kafka    - Mostra logs do Kafka"
	@echo "  make restart - Reinicia todos os serviços"
	@echo "  make clean   - Remove containers, volumes e imagens"

up:
	@echo "🚀 Iniciando Credit Query Service..."
	docker compose -f docker/docker-compose.full.yml up -d

down:
	@echo "🛑 Parando serviços..."
	docker compose -f docker/docker-compose.full.yml down

build:
	@echo "🔨 Construindo imagens..."
	docker compose -f docker/docker-compose.full.yml build --no-cache

logs:
	docker compose -f docker/docker-compose.full.yml logs -f

logs-backend:
	docker logs -f credit-backend

logs-frontend:
	docker logs -f credit-frontend

logs-db:
	docker logs -f credit-db

logs-kafka:
	docker logs -f credit-kafka

restart: down up

clean:
	@echo "🧹 Limpando tudo..."
	docker compose -f docker/docker-compose.full.yml down -v
	@echo "Imagens antigas não removidas automaticamente."
	@echo "Para remover, execute: docker rmi credit-query-service-backend credit-query-service-frontend"

