# Multi-agents
TPs Systèmes Multi-agents (Particles, Wator, Hunter)

## Architecture Globale

L'architecture globale se décompose en 4 élements :
- L'environnement :
    C'est lui qui gère et organise l'environnement d'évolution des agents. Il gère ainsi les déplacements ainsi que les demandes de voisinnage. 
Les agents sont représentés par un tableau à deux dimensions d'Agent et reste à null quand la case est vide.
Il possède égalament une liste des Agents actuellement "en vie". Par la suite j'ai également rajouté une liste des nouveaux agents ainsi que de ceux qui venaient de mourrir au tour courant. C'est une solution simple pour contourner le problème de modification concurrente d'une liste en Java. La mise à jour avec la liste courrante étant faite à chaque fin de tour.
- La vue :
    La vue est réalisée avec Swing et basé sur le DP Observer/Observable. A chaque fin de tour, le moteur notifie la vue (l'observeur donc) qui se met à jour.
Cette dernière est simplement composée d'une JFrame et d'un JPanel dans lequel est repaint à chaque mise à jour, les différents agents. Une méthode permet également d'ajouter une grille.
- Le moteur
    La classe SMA, gère l'ensemble de la simulation, c'est elle qui gère les tours ainsi que les conditions d'arrêts, les mises à jours des Agents (morts, nouveaux nés, etc) ainsi que la notification à la vue.
- L'agent
    Classe abstraite de laquelle vont hériter tous les agents des simulations. Elle met en place les paramètres de base comme la position ou la direction. 

## Particles

La chambre à particules est un environnement dans lequels des billes (les agents) se déplacent en se heurtant les unes aux autres, ou aux murs (dans le cas ou le monde n'est pas torique).
L'architecture globale est étendue avec un agent Particle, doté d'une intelligence basique. 
Chaque agent est crée avec une direction (en X et Y) aléatoire (possiblement nulle) qui indique dans quelle direction il se déplacera à chaque tour.
Cette direction est fixe, mais sera modifié à chaque collision avec une bille la direction étant inversée, en X et Y (x-1). En cas de collision avec un mur, pour simuler un angle de redirection, seule une direction est changée (Y quand la collision a lieu en X et inversement).

## Wator

Wator est une simulation d'environnement confiné (optionnellmement torique) dans lequel évoluent des thons et des requins qui peuvent se reproduire. Ces derniers ont en plus besoin de se nourrir sous peine de mourir.
Le but de cette simulation est donc d'obtenir un écosystème équilibré dans lequel les deux espèces peuvent survivre indéfiniment, malgré des phases de domination ou de quasi-extinction.

L'architecture globale est étendue avec un agent Fish qui gère les notions de reproduction, de mort, de déplacement et de voisinage (de Moore).
Un agent Tuna s'occupe de spécifier les caractèristiques propres au thon comme sa couleur (pour la vue).
Enfin, l'agent Shark, représente un requin, et son comportement propre. Ce dernier a comme objectif principal de se reproduire.
Ainsi, un requin cherchera en priorité à se reproduire, puis à se nourrir et enfin à se déplacer. A noter qu'il se déplacera systématiquement si possible, même après reproduction et/ou s'être nourrit.

## Hunter

Le hunter est une simulation dans un environnement clos, dans lequel un ou plusieurs chasseurs ont pour but d'attraper une cible.
L'architecture globale est étendue avec un agent Target, qui se déplace soit de façon autonome et aléatoire, soit contrôlée par un joueur via le clavier.
Cet agent calcule à chacun de ses déplacements une carte des distances à l'aide de l'algortihme de Dijkstra. 
Cette dernière est utilisée par les agent Hunter pour trouver le plus court chemin jusqu'à la cible et l'intercepter.
Pour cela, le chasseur étudie son voisinnage (de Moore) pour trouver le meilleur choix, c'est à dire la valeur la plus faible dans son voisinage immédiat pour le rapproche le plus de sa cible.
Un dernier agent est ajouté, l'agent Wall. Comme son nom l'indique c'est un agent qui ne fait rien, si ce n'est bloquer la place pour donner un relief à l'environnement.
