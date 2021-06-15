# Bibliothèque de la Ville
Développez le nouveau système d’information de la bibliothèque d’une grande ville.

## Pré-requis

1- Installer le kit de développement Java (JDK 8 minimum).

2- Installer Maven 4

3- Installer mySQL 8

4- Installer Postman 8.5.2 ou une version plus récente

## Contenu du repository

1- Dossier ScriptSQL_V2 avec un fichier dataAndDemoLibrary_v2.sql qui permet la création de la base de données et insertion de données:
  - Un Utilisateur avec le rôle d'Administrateur: Identifiant = 12345ID/Mot de passe:lindamoret8@gmail.com
  - Le reste qui sont de simples utilisateurs, ex : Identifiant = 67899ID/Mot de passe:jdarcot@gmail.com

2- Dossier library : contient le projet Maven/Spring Boot

3- Dossier Integration_Tests qui contient :  
  - api_test.postman_collection.json : fichier json ayant la collection de tests d'intégration à lancer sur Postman.
  - globals.json : fichiers json avec les variables globales nécessaires pour le bon fonctionnement des tests d'intégration.

## Utilisation de Spring Boot

1- Configuration dans le fichier application.properties 

	1a-Dans library/api/src/main/resources : personnaliser le host, le username et le mot de passe mySQL.	
	1b-Dans library/batch/src/main/resources : personnaliser le host(doit être différent de celui utilisé en 1a), le cron.expression, spring.mail.username/password selon son adresse Gmail(de même que le fichier: com.lavanya.batch.configuration.EmailConfiguration).
	1c-Dans library/webapplication/src/main/resources : personnaliser le host(doit être différent de celui utilisé en 1b, ici nous avons utilisé le port 8080)


2- Démarrer l'application avec ``mvn spring-boot:run``.

3- Ouvrir l'application sur localhost:8080 et se connecter avec les utilisateurs mentionnés ci-dessus.

## Déploiement

1- Packager l'application avec ``mvn package``.

2- Exécuter les applications:

	2a- api avec ``java -jar -Dserver.port=9090 api-0.0.1-SNAPSHOT.jar``.
	2b- webapplication avec ``java -jar -Dserver.port=8080 webapplication-0.0.1-SNAPSHOT.jar``.
	2c- batch avec ``java -jar -Dserver.port=8080 batch-0.0.1-SNAPSHOT.jar``.

## Tests Unitaires

Les tests unitaires peuvent être lancé directement sur le module api à l'aide de junit

## Tests d'intégration

1- Importer dans postman la collection de tests et les variables globales à l'aide des 2 fichiers json présents dans le dossier "Integration_Tests"

2- La collection importée porte le nom, "library api test".

3- Bien s'assurer que le module api est bien démarrer soit sur son ide ou en ligne de commande avec ``mvn spring-boot:run``.

4- Lancer la collection sur postman avec la commande ``run collection``.