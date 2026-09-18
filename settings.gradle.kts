pluginManagement {
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    plugins {
        id("org.springframework.boot") version springBootVersion apply false
        id("io.spring.dependency-management") version springDependencyManagementVersion apply false
        id("com.bmuschko.docker-remote-api") version "9.4.0" apply false
    }

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "harmoni"

include("backend:core:domain")
include("backend:core:application")
include("backend:driven-port")
include("backend:driven-adapter")
include("backend:driving-port")
include("backend:driving-adapter")
include("backend:spring-boot")
include("frontend")
include("smoketest")
