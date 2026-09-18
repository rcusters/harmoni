FROM eclipse-temurin:25-jre-alpine AS builder

WORKDIR /app
COPY spring-boot/build/libs/*.jar app.jar
RUN java -Djarmode=tools -jar app.jar extract --layers --launcher --destination extracted

FROM eclipse-temurin:25-jre-alpine
RUN apk add --no-cache dumb-init && addgroup -g 1001 appuser && adduser -D -u 1001 -G appuser appuser
WORKDIR /app
COPY --from=builder --chown=appuser:appuser /app/extracted/dependencies/ ./
COPY --from=builder --chown=appuser:appuser /app/extracted/spring-boot-loader/ ./
COPY --from=builder --chown=appuser:appuser /app/extracted/snapshot-dependencies/ ./
COPY --from=builder --chown=appuser:appuser /app/extracted/application/ ./
USER appuser
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC -Djava.security.egd=file:/dev/./urandom"
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["dumb-init", "--"]
CMD ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]
