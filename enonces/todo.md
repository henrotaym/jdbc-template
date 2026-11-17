# La to-do list : un CRUD en JDBC

## Contexte

On veut gérer une liste de tâches depuis le terminal. Les tâches ne vivent pas en
mémoire : elles sont stockées dans la table `task` de MySQL, et le programme ne fait
que les lire et les modifier via JDBC. Quand on relance le programme, on retrouve
ses tâches.

## La table

La table existe déjà (`initdb/01-task.sql`), avec cinq tâches de départ.

| Colonne | Type | Rôle |
| - | - | - |
| `id` | `INT AUTO_INCREMENT` | numéro de la tâche, donné par MySQL |
| `name` | `VARCHAR(100)` | ce qu'il y a à faire |
| `done` | `BOOLEAN` | faite (`TRUE`) ou pas (`FALSE`, par défaut) |

## Le menu

Le programme affiche ce menu, lit le choix, l'exécute, puis réaffiche le menu jusqu'à
ce qu'on choisisse 0.

```
=== Ma to-do list ===
1. Lister les tâches
2. Ajouter une tâche
3. Marquer une tâche comme faite
4. Renommer une tâche
5. Supprimer une tâche
0. Quitter
Choix :
```

| Choix | Ce qui se passe |
| - | - |
| 1 | Affiche toutes les tâches par numéro croissant, ou `Aucune tâche.` |
| 2 | Demande un nom, crée la tâche (pas faite) et affiche son numéro |
| 3 | Demande un numéro et marque la tâche comme faite |
| 4 | Demande un numéro puis un nouveau nom |
| 5 | Demande un numéro et supprime la tâche |
| 0 | Affiche `À bientôt !` et quitte |

Une tâche s'affiche ainsi : `[ ] 2. Lire le déroulé JDBC` si elle n'est pas faite,
`[x] 1. Installer le dev container` si elle l'est.

## Les règles

- Un nom vide ou de plus de 100 caractères est refusé, sans rien toucher en base.
- Un numéro qui n'est pas un nombre est refusé.
- Un numéro qui n'existe pas affiche `Aucune tâche n°42.` : c'est le nombre de lignes
  touchées par la requête qui le dit, pas une lecture préalable.
- Un choix inconnu affiche `Choix inconnu.` et réaffiche le menu.

## Exemple de session

Avec les cinq tâches de départ :

```
Choix : 2
Nom : Acheter du pain
Tâche n°6 ajoutée.

Choix : 3
Numéro : 2
Tâche n°2 faite.

Choix : 5
Numéro : 42
Aucune tâche n°42.

Choix : 1
[x] 1. Installer le dev container
[x] 2. Lire le déroulé JDBC
[ ] 3. Écrire la première requête
[ ] 4. Comprendre les curseurs
[ ] 5. Rendre le CRUD
[ ] 6. Acheter du pain
```

(Le menu, réaffiché entre chaque choix, est omis ici.)

## Critères d'acceptation

- Les six choix du menu fonctionnent comme décrit, et les changements sont toujours
  là après avoir relancé le programme.
- Toute requête qui contient une saisie de l'utilisateur passe par un
  `PreparedStatement`. Aucune concaténation de saisie dans du SQL.
- Une seule connexion est ouverte au démarrage, et elle est fermée en quittant, même
  en cas d'erreur.
- Tout le SQL est dans une seule classe. Le menu ne contient aucune requête, et cette
  classe n'affiche rien.
- Une tâche lue en base devient un objet Java, avant d'être affichée.
- Chaque `Statement`, `PreparedStatement` et `ResultSet` est fermé après usage.

## Bonus

- Ajouter un choix « Supprimer les tâches faites », qui affiche combien de tâches ont
  été supprimées.
- Ajouter un choix « Lister les tâches à faire » qui filtre en SQL (`WHERE`), pas en
  Java.
