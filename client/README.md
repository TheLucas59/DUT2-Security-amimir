# Amimir - Client

## Arguments et structures

### Structure de configuration

- Configuration dans un dossier, de base `<root folder>/config`
	- pubkey, privkey, uuid du client
	- liste des clés connues (= "contacts") avec un alias

### Arguments

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
- `-s, --server <server url>` : Spécifier un serveur différent du serveur par défaut (localhost)
- `--config <config folder>` : Spécifier un dossier de configuration différent de celui par défaut (./config)
		
## Build / Run

Run :
```bash
mvn clean compile exec:java -Dexec.args="<arguments>"
```

Build :
```bash
mvn clean compile assembly:single
```