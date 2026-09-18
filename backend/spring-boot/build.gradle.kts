plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

springBoot {
    mainClass.set("be.opcodes.harmoni.springboot.HarmoniApplication")
}

tasks.bootJar {
    layered {
        enabled = true
    }
}

val dockerRegistry = project.findProperty("dockerRegistry")?.toString() ?: "ghcr.io"
val dockerRepository = project.findProperty("dockerRepository")?.toString() ?: "rcusters"
val imageName = "harmoni-backend"
val imageTag = project.version.toString().ifEmpty { "latest" }

tasks.register<Exec>("dockerBuild") {
    group = "docker"
    description = "Builds the Docker image for the backend"
    dependsOn(tasks.bootJar)
    workingDir = file("$rootDir/backend")

    val localTag = "$imageName:$imageTag"
    val localLatest = "$imageName:latest"
    val registryTag = "$dockerRegistry/$dockerRepository/$imageName:$imageTag"
    val registryLatest = "$dockerRegistry/$dockerRepository/$imageName:latest"

    commandLine("docker", "build", "--platform", "linux/amd64", "-t", localTag, "-t", localLatest, "-t", registryTag, "-t", registryLatest, ".")
}

tasks.register<Exec>("dockerPush") {
    group = "docker"
    description = "Pushes the Docker image to GitHub Container Registry"
    dependsOn("dockerBuild")
    workingDir = file("$rootDir/backend")

    val registryTag = "$dockerRegistry/$dockerRepository/$imageName:$imageTag"
    val registryLatest = "$dockerRegistry/$dockerRepository/$imageName:latest"
    commandLine("sh", "-c", "docker push $registryTag && docker push $registryLatest")
}

tasks.register<Exec>("dockerRun") {
    group = "docker"
    description = "Runs the Docker container locally"
    dependsOn("dockerBuild")
    commandLine("docker", "run", "--rm", "-p", "8080:8080", "--name", "harmoni-backend", "-e", "SPRING_PROFILES_ACTIVE=docker", "harmoni-backend:latest")
}

dependencies {
    implementation(project(":backend:core:domain"))
    implementation(project(":backend:core:application"))
    implementation(project(":backend:driving-port"))
    implementation(project(":backend:driven-port"))
    implementation(project(":backend:driving-adapter"))
    implementation(project(":backend:driven-adapter"))

    implementation(platform("org.springframework.boot:spring-boot-dependencies:${property("springBootVersion")}"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
