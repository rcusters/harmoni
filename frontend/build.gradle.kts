plugins {
    base
}

val dockerRegistry = project.findProperty("dockerRegistry")?.toString() ?: "ghcr.io"
val dockerRepository = project.findProperty("dockerRepository")?.toString() ?: "rcusters"
val imageName = "harmoni-frontend"
val imageTag = project.findProperty("dockerTag")?.toString() ?: "latest"

tasks.register<Exec>("npmInstall") {
    group = "build"
    description = "Install npm dependencies"
    workingDir = projectDir
    commandLine("npm", "ci")
    inputs.file("package.json")
    inputs.file("package-lock.json")
    outputs.dir("node_modules")
}

tasks.register<Exec>("npmBuild") {
    group = "build"
    description = "Build the Next.js application"
    dependsOn("npmInstall")
    workingDir = projectDir
    commandLine("npm", "run", "build")
    inputs.files(fileTree("app"), fileTree("components"), fileTree("lib"), fileTree("types"), fileTree("public"))
    inputs.files("next.config.js", "tsconfig.json", "package.json")
    outputs.dir(".next")
}

tasks.register<Exec>("dockerBuild") {
    group = "docker"
    description = "Build Docker image for frontend"
    workingDir = projectDir

    val localTag = "$imageName:$imageTag"
    val localLatest = "$imageName:latest"
    val registryTag = "$dockerRegistry/$dockerRepository/$imageName:$imageTag"
    val registryLatest = "$dockerRegistry/$dockerRepository/$imageName:latest"

    commandLine("docker", "build", "--platform", "linux/amd64", "-t", localTag, "-t", localLatest, "-t", registryTag, "-t", registryLatest, ".")
}

tasks.register<Exec>("dockerPush") {
    group = "docker"
    description = "Push Docker image to GitHub Container Registry"
    dependsOn("dockerBuild")
    workingDir = projectDir

    val registryTag = "$dockerRegistry/$dockerRepository/$imageName:$imageTag"
    val registryLatest = "$dockerRegistry/$dockerRepository/$imageName:latest"
    commandLine("sh", "-c", "docker push $registryTag && docker push $registryLatest")
}

tasks.named<Delete>("clean") {
    delete(".next", "out")
}

tasks.named("build") {
    dependsOn("npmBuild")
}

tasks.named("assemble") {
    dependsOn("npmBuild")
}
