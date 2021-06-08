# Bibliothèque de la Ville
Développez le nouveau système d’information de la bibliothèque d’une grande ville.

## Pré-requis

1- Installer le kit de développement Java (JDK 8 minimum).

2- Installer Maven 4

3- Installer mySQL 8

## Contenu du repository

1- Dossier ScriptSQL_V2 avec un fichier dataAndDemoLibrary_v2.sql qui permet la création de la base de données et insertion de données:
  - Un Utilisateur avec le rôle d'Administrateur: Identifiant = 12345ID/Mot de passe:lindamoret8@gmail.com
  - Le reste qui sont de simples utilisateurs, ex : Identifiant = 67899ID/Mot de passe:jdarcot@gmail.com

2- Dossier library : contient le projet Maven/Spring Boot

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