# Compte Rendu - Projet Sécurité 2021

## Description du projet

Amimir - Un chat sécurisé où le serveur n'a à aucun moment le message en clair ou la possibilité de le décrypter, et seuls les utilisateurs peuvent décrypter les messages qui leurs sont adressés. 

Loïc DEMAY ([loic.demay.etu@univ-lille.fr](mailto:loic.demay.etu@univ-lille.fr))

Lucas PLÉ ([lucas.ple.etu@univ-lille.fr](mailto:lucas.ple.etu@univ-lille.fr))

Nous avons utilisé la librairie Jackson pour le client, pour pouvoir lire le json renvoyé par le serveur REST. Le serveur REST utilise lui Jersey, JDBI et Jakarta parce que nous avons réutilisé en partie le squelette du serveur REST fait en cours de programmation répartie.

Nous avons apprécié gérer des problématiques de sécurité en informatique, mais également gérer un serveur REST. Ce projet nous a permis de travailler sur les deux en même temps et nous avons apprécié réaliser ce projet.

### Difficultés rencontrées

- Modéliser l'architecture avec le serveur pour avoir un ensemble cohérent

- L'encodage des Strings qui est inconsistent après le passage au Cipher, qui ensuite n'était pas correctement restitué par le serveur / Jackson.

- L'utilisation de la librairie Jackson.

### État actuel du projet

Sur le projet actuel, on peut :
- Créer une identité, l'exporter pour être ajouté en tant que contact
- Ajouter / enlever des contacts, lister les contacts
- Envoyer un message
- Lire les messages qu'un contact nous a envoyé

On ne peut par contre pas :
- Utiliser le projet de façon graphique
- Faire plusieurs commandes a la fois (ce qui rends l'utilisation plutôt lente et non user friendly)

## Mode d'emploi

**Pré requis : Java 11, Maven**

### Serveur REST

Pour compiler le serveur il suffit de se mettre dans le dossier `rest` et d'exécuter :
```bash
mvn clean compile package
```

Le projet sera compilé puis empaqueté dans un fichier `jar` dans le dossier `target`.

Pour lancer le serveur il suffit juste de lancer le jar :
```bash
java -jar amimir_rest-1.0-SNAPSHOT.jar
```

### Client

Pour compiler le client il suffit de se mettre dans le dossier `client` et d'exécuter :
```bash
mvn clean compile assembly:single
```

Le projet sera compilé puis empaqueté dans un fichier `jar` dans le dossier `target`.

Pour utiliser le client, il suffit de lancer le jar avec ses arguments :

```bash
java -jar amimir_client-1.0-SNAPSHOT-jar-with-dependencies.jar <arguments>
```

L'utilisation se découpe en trois commandes majeures :

read : Lire des messages
- `-a <auteur>` (UUID / Contact) : Auteur du message

send : envoyer un message
- `-d <contact>` : Destinataire du message
- `-m <message>` : Message a envoyer

config : gerer la configuration
- `--generate` : Crée une nouvelle configuration dans le dossier 'config' ou est situé le jar du client.
- `--import <chemin>` : Ajoute aux contacts le contact contenu dans un fichier.
- `--export-identity` : Exporte la configuration actuelle ou est situé le jar du client.
- `--list-contacts` : Liste les contacts actuels

Arguments globaux :
- `-s, --server <server url>` : Spécifier un serveur REST différent du serveur par défaut (`localhost:8080/api/v1/messages`)
- `--config <config folder>` : Spécifier un dossier de configuration différent de celui par défaut (`./config`)