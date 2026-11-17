# JDBC : Java 25 + MySQL

## Démarrer

Ouvrir ce dossier dans VS Code, puis « Dev Containers: Reopen in Container ».
Deux conteneurs démarrent : `app` (Java 25, là où on code) et `db` (MySQL 9.7).

## Lancer une étape

```bash
javac -d out src/main/java/*.java
java -cp "out:lib/*" Step1Standard
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
