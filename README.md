# HelpBoard service

### Production deployment

Define the following env variables:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://host_name:5432/db_name
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
SPRING_REDIS_HOST=
SPRING_REDIS_PORT=
SPRING_REDIS_PASSWORD=
AUTH_REMEMBERME_SECRET=random_uuid
SPRING_SOCIAL_FACEBOOK_APPID=
SPRING_SOCIAL_FACEBOOK_APPSECRET=
```

### Local development

1. Run local postgresql by `cd docker-local` and `docker-compose up -d`
2. Run `BoardApplication`

Env variables:
```
SPRING_PROFILES_ACTIVE=dev
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgres
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
SPRING_REDIS_HOST=localhost
SPRING_REDIS_PORT=6379
SPRING_REDIS_PASSWORD=pass
AUTH_REMEMBERME_SECRET=70a6e478-bcde-4a51-beaa-b657021d46a7
```

Additionally, if you want to develop spring social login, provide those env variables:
```
SPRING_SOCIAL_FACEBOOK_APPID=
SPRING_SOCIAL_FACEBOOK_APPSECRET=
```

### Redis ops

Delete all sessions:

```
redis-cli keys '*' | xargs redis-cli del
```

### GCR deployment
Deploy current build do GCR registry:

1. Prerequisites:
- Docker
- *[gcloud CLI](https://cloud.google.com/sdk/docs/install)*  
- *[docker-credential-helper](https://cloud.google.com/container-registry/docs/advanced-authentication#docker_credential_helper)*  


2. Build and deploy to the registry preconfigured in build.gradle
```
./gradlew jib
```

3. Force rolling update on GKE using one of following
- run rolling update from the GCP Web Console
- run rolling update procedure using kubectl tool

