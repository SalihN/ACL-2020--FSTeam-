# ACL-2020-FSTeam

#### Auteurs:
* Emanuel Gady
* Salih Nascimento
* Alexis Richer
* Goetz Alexandre

Projet M1 Informatique: Jeu avec un labyrinthe (similaire à un Pacman)  

Jeu de type pacman. Vous êtes un héro coincé dans un labyrinthe remplit de monstre et vous cherchez à attraper le trésor !

## Compilation: 
Compilation et lancement du jar du jeu  
```bash
ant
```
ou
```bash
ant run
```
Compliation et création d'un jar  
```bash
ant jar
```
Compilation du jeu  
```bash
ant compile
``` 

## Création de niveau :

Les niveaux doivent être placés dans ressources/mazes.  
Leur nom doit être de la forme "mazeX.txt"  
X correspond au numéro du niveau  
Les niveaux sont joué l'un à la suite de l'autre ( X + 1 )  

Le deux premières lignes du fichier correspondent respectivement à la haut et la largeur du labyrinthe  

les lignes suivantes représentent le labyrinthe  
Il faut utiliser les symboles suivant pour remplir le labyrinthe:  
* w : mur
* n : sol normal
* t : sol de trésor
* h : sol soignant le héro
* f : sol figeant les monstres
* s : sol ralentiseur de monstre
* m : Monstre normal 
* p : Position du héro 
  * /!\ seulement le "p" le plus en bas à droite est retenu
* a : Gros monstre 
* g : Monstre gardien
* k : Monstre kidnappeur

*Exemple de labyrinthe:*  
11  
11  
nnnnnnnnnnn  
nngnnnnnnnn  
nantnnnnnnn  
nnnnnnnngnw  
nnnnnnnnnhn  
nnnnnpnnnnn  
nnnnnnnnnnn  
nnnnnnnnnnn  
nnwwwwnnnnn  
nnnnnnnnnnn  
nmnnknnnann  

