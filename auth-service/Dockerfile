# Étape 1 : Build (optionnel si vous compilez en dehors du container)
# Vous pouvez supprimer cette étape si vous faites déjà mvn package en local

# Étape 2 : Image runtime (recommandée : utiliser une image JRE plutôt que JDK)
FROM eclipse-temurin:17-jre

# Définir le dossier de travail
WORKDIR /app

# Créer un utilisateur non-root (bonne pratique sécurité)
RUN addgroup --system appgroup && \
    adduser --system --ingroup appgroup --no-create-home appuser

# Copier le JAR (utilisez le nom exact généré par Maven)
COPY target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar

# Changer le propriétaire du fichier JAR (sécurité)
RUN chown appuser:appgroup auth-service.jar

# Utiliser l'utilisateur non-root
USER appuser

# Exposer le port (c'est informatif, pas obligatoire mais utile)
EXPOSE 8081

# Lancer l'application
ENTRYPOINT ["java", "-jar", "auth-service.jar"]
