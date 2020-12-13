 # ACL-2020-FSTeam

<p align="center">
 

<img src="https://raw.githubusercontent.com/SalihN/ACL-2020-FSTeam/main/resources/images/fsteam.png" width="50%" height="50%">
</p>

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
## Comment jouer ? 
#### Navigation dans les menus
Utilisez les flèches directionelles pour naviguer dans les menus et appuyer sur Entrer pour valider votre choix  

#### Commandes du personnage et labyrinthe
Utilisez les touches z,q,s,d (ou w,a,s,d) pour se déplacer avec le personnage   
Appuyez sur espace pour lancer une boule de feu 
Parvenez à récupérer tous les trésors de tout les labyrinthe sans mourir et avant la fin du compte à rebours. Plusieurs bonus sont dispersés dans le labyrinthe pour vous aidez dans votre quête  
Dans les menus utilisez z et q ou les flèches haut et bas pour se déplacer dans les menus  

## Créer et modifier des labyrinthes :

#### Où placer les fichiers de labyrinthe et les modifiers

Vous pouvez ajouter ou modifier de nouveaux niveaux vous même, pour cela ajouter les dans le dossier /ressources/mazes  
Le nom du fichier doit être de la forme "mazeX.txt" où X est un nombre entier avec 0 inclu  
Les niveaux sont joué dans l'ordre, donc si vous voulez modifier le niveau 0 il faut modifier le fichier "maze0.txt"
Si vous voulez ajouter un nouveau niveau et y jouer faite en sorte que sont nombre soit le suivant dans la liste, sinon il ne sera pas joué   

#### Comment formater le fichier de labyrinthe

Les deux premières lignes du labyrinthe doivent correspondre respectivement à: la hauteur et la largeur de celui ci  


Il est recommandé d'avoir soit un labyrinthe au format de la fenêtre afin d'éviter des dépassement de fenêtres  
La fenêtre  de base est rectangulaire et de taille 700x700

les lignes suivantes représentent le labyrinthe  
Il faut utiliser les symboles suivant pour remplir le labyrinthe:  
* w : mur
* n : sol normal
* t : sol de trésor
* h : sol soignant le héro
* f : sol figeant les monstres
* s : sol ralentiseur de monstre
* j : sol avec des piques
* m : Monstre normal 
* p : Position du héro 
  * /!\ seulement le "p" le plus en bas à droite est retenu
* a : Gros monstre 
* g : Monstre gardien
* k : Monstre kidnappeur
* c : Coins

**/!\ Si par contre il manque des lignes le niveau ne se lancera pas**  
Par contre si la taille d'une ligne ne correspond pas à la largeur donnée elle sera complétée avec des NormalFloor  


**Exemple de labyrinthe:**  
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

Ce qui nous donne:

<img src="https://raw.githubusercontent.com/SalihN/ACL-2020-FSTeam/main/resources/images/maze1Exemple.png" width="50%" height="50%">


####Comment lancer les tests :

Récupérer EasyMock sur le site officiel : https://easymock.org/

Récupérer également Objenesis : http://objenesis.org/download.html

Ajouter les deux en librairie.