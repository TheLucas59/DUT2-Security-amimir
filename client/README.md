# Amimir - Client

## Arguments et structures

### Structure de configuration

- Configuration dans un dossier, de base `<root folder>/config`
	- pubkey, privkey, uuid du client
	- liste des clés connues (= "contacts") avec un alias

### Arguments

- Connexion au serveur
	- `-s`, `--server` Préciser l'url du serveur auquel on récupère les messages (sinon utiliser une url de base? fichier de config?)

- Gérer la configuration du client
	- `--export-identity` Exporter le fichier d'identité (pubkey, uuid)
	- `--config <folder>` Utiliser le dossier passé en paramètre a la place de la configuration de base (celle dans le dossier courant)
	- `-i <file>`, `--import <file>` Importer un fichier d'identité

- Afficher les messages selon les paramètres :
	- `-a <author>` "author" : uuid ou alias (si pas un uuid valide, alors check dans les alias, sinon erreur)
	- `-d <dest>` si précisé, sinon dest = client actuel

## Comportement

- Generer les clés (first run)
	- si aucune configuration n'est trouvée (dans le dossier de l'application)
	- si la configuration passée en paramètre n'existe pas
		- si la configuration passée est invalide, erreur et ne rien faire pour ne pas damage une installation possible existante.