FROM openjdk:23-jdk-oracle AS builder

ARG COMPILE_DIR=/compileddir

WORKDIR ${COMPILE_DIR}

COPY mvnw . 
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

RUN ./mvnw package -Dmaven.test.skip=true

FROM openjdk:23-jdk-oracle

ARG WORK_DIR=/app

WORKDIR ${WORK_DIR}

COPY --from=builder /compileddir/target/movies-0.0.1-SNAPSHOT.jar movies.jar

ENV PORT=8080

EXPOSE ${PORT}

ENTRYPOINT java -jar movies.jar