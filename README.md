# ArtifactUP
A Progressive Web App (PWA) showcasing artifacts from various museums at the University of Pretoria.

# Requirements
- Java SDK 21
- Docker 20.10+

# Running the Backend
- Insure you have have docker running
- `cd backend`
- `.\mvnw spring-boot:run` or `./mvnw spring-boot:run`

# Starting local s3 instance
- `cd backend`
- docker compose -f compose_localstack.yml up
- aws --endpoint-url=http://localhost:4566
- to upload images `aws --endpoint-url=http://localhost:4566 s3 cp ./images/ s3://museum-artefacts/ --recursive --exclude "*" --include "*.jpg"`
- to list all artefacts in folder `aws --endpoint-url=http://localhost:4566 s3 ls s3://museum-artefacts/`

# Api Docs
- navigate to http://localhost:8080/swagger-ui/index.html#/

psql -U postgres -d museum_db -f large_sql.sql
set AWS_REGION=af-south-1