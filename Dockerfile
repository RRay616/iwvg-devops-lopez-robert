# Entorno de PRODUCCIÓN

# ==ETAPA 1: Construcción del JAR con Maven==
   # Imagen combinada: maven + jdk21 de eclipse
FROM maven:3.9.9-eclipse-temurin-21 AS build
   # Directorio de trabajo dentro del contenedor
WORKDIR /app
   # Solo se copia el pom.xml al directorio de trabajo
COPY pom.xml ./
   # Baja las dependencias sin conexión y en modo Batch (sin asistencia)
RUN mvn dependency:go-offline -B
   # Copia los fuentes del proyecto
COPY src ./src
   # Limpia y empaqueta (se crea el *.jar), sin ejecutar los tests
RUN mvn clean package -DskipTests && cp target/*.jar /app/app.jar

# ==ETAPA 2: Configuración de la app Java==
   # Contenedor solo con JRE, para hacerlo mas pequeño
FROM eclipse-temurin:21-jre-alpine
   # Directorio de trabajo dentro del contenedor
WORKDIR /app
   # Crea un usuario y grupo no privilegiados para ejecutar la aplicación
RUN addgroup -S app && adduser -S app -G app
   # Copia el archivo JAR generado en el contenedor de construcción
COPY --from=build /app/app.jar app.jar
   # Ejecuta el contenedor con un usuario sin privilegios
USER app
   # Este contenedor escucha el puerto indicado
EXPOSE 8080
   # Comprueba periódicamente que la aplicación está funcionando correctamente
HEALTHCHECK --interval=120s --timeout=5s --start-period=60s --retries=3 \
  CMD wget -qO- http://localhost:8080/actuator/health || exit 1
   # Define un comando para inicializar el contenedor: Java + opciones + JAR
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]


# ------------------------------------- COMANDOS ----------------------------------------------------------
# Construir la imagen, ATENCION!!! existe un punto al final que se debe incluir
#> docker build -t devops:latest .

# Crea y arrancar el contenedor a partir de la imagen
#> docker run -d --name devops1 -p 8080:8080 devops

# Arranca el contenedor
#> docker start devops1