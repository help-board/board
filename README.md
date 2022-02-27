# HelpBoard service

### Local development

1. Run local postgresql by `cd docker-local` and `docker-compose up -d`
2. Run `BoardApplication`

Env variables:
```
SPRING_PROFILES_ACTIVE=dev
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgres
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
```

### Redis ops

Delete all sessions:

```
redis-cli keys '*' | xargs redis-cli del
```
