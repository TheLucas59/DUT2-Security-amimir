# Amimir - Serveur REST

## Structure de l'API

| URI              | Opération | MIME                  | Requête                                  | Réponse                                               |
|------------------|-----------|-----------------------|------------------------------------------|-------------------------------------------------------|
| /messages        | GET       | <-application/json    |                                          | Tout les messages du serveur                          |
| /{id}            | GET       | <-application/json    |                                          | Message avec cet id                                   |
| /{author}/{dest} | GET       | <-application/json    |                                          | Liste des messages de cet auteur pour ce destinataire |
| /messages        | POST      | <-/->application/json | Auteur, destinataire, message (encrypté) | Le message créé (avec son ID et son timestamp)        |

## Build / Run

Run :
```bash
mvn clean compile exec:java
```

Build :
```bash
mvn clean compile package
```