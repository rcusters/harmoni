dependencies {
    implementation(project(":backend:core:domain"))
    implementation(project(":backend:driven-port"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
