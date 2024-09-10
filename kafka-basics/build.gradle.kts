plugins {
    id("java-library")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:3.8.0")

    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("org.slf4j:slf4j-simple:2.0.16")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview") // Enable preview features for compilation
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--enable-preview")  // Enable preview features for testing
}

tasks.withType<JavaExec> {
    jvmArgs("--enable-preview")  // Enable preview features for running applications
}