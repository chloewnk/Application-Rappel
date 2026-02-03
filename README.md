# Bienvenue dans notre projet Papillon ! 
## Auteurs : Antoine Desesquelles & Chloé WIENCEK

Ce projet est une application de penses-bêtes permettant à un utilisateur d'ajouter / modifier ou supprimer ses propres rappels.

**Base de données** : MariaDB 

Cloner notre projet : 
```bash
git clone https://grond.iut-fbleau.fr/wiencek/SAE31_2025/
```

Pour compiler le projet : 

```bash
make
```

Pour executer le projet (execution du .jar) : 
```bash
make run
```

Supprimer le dossier build/ doc/ et le .jar : 
```bash
make clean
```

Générer la javadoc : 
```bash
make javadoc
make seedoc
```

Accompagné de ce projet, le rapport est rédigé en LaTeX. Pour compiler le .tex : 
```bash
make pdf
```


Le dossier **src/iutfbleau/fr/papillon/** : ce dossier comporte tous les .java

Le dossier **lib/** : l'archive MariaDB

Le dossier **lib-extracted/** : comporte les fichier de l'archive MariaDB qui sont utilisés lors de la compilation du .jar

Le dossier **doc/** : tous les fichiers de la javadoc

Le dossier **documentation/** : le rapport

Le dossier **build/** : les fichiers compilés avec avoir fait make.