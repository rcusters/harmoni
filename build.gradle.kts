plugins {
    base
}

val backendProjects = allprojects.filter { it.path.startsWith(":backend") }
val smokeTestProjects = allprojects.filter { it.path.startsWith(":smoketest") }
val javaProjects = backendProjects + smokeTestProjects

tasks.register("backend") {
    group = "build"
    description = "Build all backend modules and Docker image"
    backendProjects.forEach {
        dependsOn(it.tasks.matching { task -> task.name == "build" })
    }
    dependsOn(":backend:spring-boot:dockerBuild")
}

tasks.register("frontend") {
    group = "build"
    description = "Build frontend"
    dependsOn(":frontend:build")
}

tasks.register("buildAll") {
    group = "build"
    description = "Build both backend and frontend"
    dependsOn("backend", "frontend")
}

tasks.register<Exec>("dockerComposeUp") {
    group = "docker"
    description = "Start all services with docker-compose"
    dependsOn("buildAll")
    workingDir = projectDir
    commandLine("docker-compose", "up", "-d")
}

tasks.register<Exec>("dockerComposeDown") {
    group = "docker"
    description = "Stop all services with docker-compose"
    workingDir = projectDir
    commandLine("docker-compose", "down")
}

tasks.register<Exec>("dockerStopAll") {
    group = "docker"
    description = "Stop all running Docker containers"
    commandLine("sh", "-c", "docker stop \$(docker ps -q) 2>/dev/null || true")
    isIgnoreExitValue = true
}

tasks.register<Exec>("dockerCleanup") {
    group = "docker"
    description = "Stop all containers and remove unused resources"
    commandLine("sh", "-c", "docker stop \$(docker ps -q) 2>/dev/null || true && docker system prune -f")
    isIgnoreExitValue = true
}

tasks.register<Exec>("stopDaemons") {
    group = "build"
    description = "Stop all Gradle daemon processes"
    commandLine("sh", "-c", "./gradlew --stop")
}

tasks.register("dockerPush") {
    group = "docker"
    description = "Push both backend and frontend Docker images to GitHub Container Registry"

    val registry = project.findProperty("dockerRegistry")?.toString() ?: "ghcr.io"
    val repository = project.findProperty("dockerRepository")?.toString() ?: "rcusters"

    dependsOn(":backend:spring-boot:dockerPush")
    dependsOn(":frontend:dockerPush")

    doFirst {
        println("Pushing Docker images to $registry/$repository")
        println("Backend: $registry/$repository/harmoni-backend")
        println("Frontend: $registry/$repository/harmoni-frontend")
    }
}

tasks.named("build") {
    dependsOn("buildAll")
}

configure(javaProjects) {
    apply(plugin = "java-library")

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
        group = "be.opcodes.harmoni"
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

configurations.configureEach {
    resolutionStrategy {
        failOnVersionConflict()
    }
}
