# API_Labo04_SMTP

## Description 
MailRobot est un programme qui vous permet de vous faire passer pour quelqu'un d'autre et d'envoyer des
blagues par mail à des victimes de vos choix. 

PS : pas de connaissance particulière demandée! L'utilisation est très simple.

## MockMock 
MockMock est un serveur SMTP fictif qui permet de simuler l'envoie des mails (sans les enovoyer réellement).
Il fournit une interface Web qui affiche les e-mails envoyés ainsi que leur contenu. C'est un server 
multiplateforme basé sur java.

## Configuration 
Le fichier de configuration se trouve dans le dossier config. Il contient 3 fichiers :
 * config.properties : configuration du server SMTP. Il contient l'adresse du server SMTP, le numéro de port de
   connexion, le nombre de groupe à former ainsi que l'adresse e-mail du témoin.
 * messages : contient les blagues, constituée d'un sujet et d'un message, qui seront envoyées. 
 * victims  : contient l'addresse e-mails des victimes.

Il est possible de modifier les données de configuration, cependant il est essentiel de garder la même structure 
(sujet, message, séparation de chaque blague par "=="). De plus, il faut avoir aumoins une blague et respecter la
taille minimun d'un groupe : avoir aumoins trois adresses e-mails.

## Utilisation
Après avoir modifier le fichier de configuration, il faut tout simplement exécuter la commande suivante depuis le 
dossier src afin d'envoyer les blagues automatiquement : 
* java -jar target/Labo_4_SMTP-1-0-SNAPSHOT.jar 

Il est également possible de faire des tests avant d'envoyer les blagues. Pour ce faire, vous pouvez utiliser le 
 serveur MockMock qui se trouve dans le dossier docker.

#### Procédure pour effectuer un test
* Installer docker et le lancer
* Depuis le dossier docker, lancer les commandes suivantes : ./build-image.sh ensuite ./run-container.sh.
* Modifier le fichier de configuration afin d'utiliser l'adresse ip 127.0.0.1 et le numéro de port 8281
* Lancer l'application depuis le dossier src en utilisant la commande suivante :
  java -jar target/Labo_4_SMTP-1-0-SNAPSHOT.jar

Afin de consulter les e-mails reçus par le serveur MockMock, connectez vous sur le navigateur à l'adresse 
http://localhost:8282/


## Implémentation du programme
### Diagramme de classe


### Descriptions des classes dans les différents packages
##### Package config 
* ConfigurationManager : s'occupe de lire les fichiers de configuration et de faire la parsing
##### Package mail
* Groupe : représente l'ensemble des victimes
* Message : représente un mail avec ces principaux composants (expéditeur,destinateur, sujet, 
            corps du message, cc, bcc ).
* Person : représente une personne (nom, prénom et adresse)

##### Package prank
* Prank : représente une blague composée d'un sujet et d'un message
* PrankGenerator : permet de générer les groupes de victimes et les blagues à leur envoyer.

##### Package smtpClient
* SmtpClient : Permet d'envoyer un mail en respectant le protocole SMTP

MailRobot : programme principal 

## Exemple de dialogue

## Références 
* Docker : https://www.youtube.com/playlist?list=PLfKkysTy70QbseGZcVbpTXhas2xrXKk61 
* Labo SMTP code: https://www.youtube.com/watch?v=OrSdRCt_6YQ&list=PLfKkysTy70QY_C0t9avTuEsLVVObxOtTM

