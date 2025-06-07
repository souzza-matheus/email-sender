# EmailSender API

Este projeto é uma API de envio de e-mails construída em **Spring Boot**, utilizando arquitetura limpa, autenticação com JWT, controle de rate limit com Redis e integração com serviços externos de SMTP.

## ✨ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Mail (SMTP)
- Redis (Upstash)
- JWT (Json Web Token)
- Clean Architecture (Domain, UseCase, Infra, Controller)
- Docker

## 🚀 Funcionalidades

- Envio de e-mails via SMTP configurável.
- Autenticação com JWT service-to-service.
- Geração segura de tokens com proteção por API Key.
- Controle de requisições por IP com Redis (Rate Limit).
- Exceções centralizadas com `@RestControllerAdvice`.
- Configuração 100% externalizada via variáveis de ambiente.

## ⚙️ Configuração

Todas as configurações são feitas via variáveis de ambiente:

| Variável | Descrição |
| -------- | --------- |
| SMTP_USER | Usuário SMTP |
| SMTP_PASS | Senha SMTP (App Password se usar Gmail) |
| SMTP_HOST | Host SMTP (ex: smtp.gmail.com) |
| SMTP_PORT | Porta SMTP (ex: 587) |
| EMAIL_TO | E-mail que receberá as mensagens |
| REDIS_HOST | Host do Redis (Upstash) |
| REDIS_PORT | Porta do Redis |
| REDIS_PASSWORD | Senha do Redis |
| JWT_SECRET | Secret do JWT em Base64 |
| JWT_GENERATION_SECRET | Senha protegida para geração dos tokens |

Exemplo de configuração local (em `application.properties`):

```properties
spring.mail.host=${SMTP_HOST:smtp.gmail.com}
spring.mail.port=${SMTP_PORT:587}
spring.mail.username=${SMTP_USER}
spring.mail.password=${SMTP_PASS}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

app.email.to=${EMAIL_TO}

spring.redis.host=${REDIS_HOST}
spring.redis.port=${REDIS_PORT}
spring.redis.password=${REDIS_PASSWORD}

jwt.secret=${JWT_SECRET}
jwt.generation.secret=${JWT_GENERATION_SECRET}
```
## 🔐 Geração de Tokens JWT

A API possui um endpoint protegido para geração de novos tokens, com proteção via header de API Key:

- Rota: `POST /token/generate`
- Header obrigatório: `X-SECRET-KEY`

## 🛡️ Segurança

- Tokens JWT assinado com HS256
- Secret 100% externalizado
- Rate Limit por IP controlado com Redis
- Exceções personalizadas e seguras
- Docker isolado

## 📦 Testes Locais

```bash
./mvnw clean package
docker build -t email-sender .
docker run -p 8080:8080 --env-file=.env email-sender
