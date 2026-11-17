# JDBC : Java 25 + MySQL

## Démarrer

Ouvrir ce dossier dans VS Code, puis « Dev Containers: Reopen in Container ».
Deux conteneurs démarrent : `app` (Java 25, là où on code) et `db` (MySQL 9.7).

## Lancer le programme

Le plus simple : le lien « Run » au-dessus de la méthode `main` (le driver MySQL de `lib/`
est ajouté automatiquement). En ligne de commande :

```bash
javac -d out src/main/java/*.java
java -cp "out:lib/*" Main
```

## Ouvrir MySQL en ligne de commande

```bash
mysql -h db -ustudent -pstudent todo
```

## Remettre la base à zéro

Depuis la machine hôte, dans ce dossier :

```bash
docker compose down -v
```

Le script `initdb/01-schema.sql` recrée la table et les données au prochain démarrage.
