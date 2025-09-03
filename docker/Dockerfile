# Minimal Dockerfile to run the fat-jar with Spark in local mode
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY target/scala-2.12/scala-spark-taxi-pipeline-assembly.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
