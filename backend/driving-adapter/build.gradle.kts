plugins {
    id("io.spring.dependency-management")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${property("springBootVersion")}")
    }
}

dependencies {
    implementation(project(":backend:core:domain"))
    implementation(project(":backend:driving-port"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}
