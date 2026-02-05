plugins {
    id("java")
}

group = "ca.sullyq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    compileOnly(files("libs/HytaleServer.jar"))
    compileOnly(files("libs/tinymessage-2.0.0.jar"))

    // JSR305 annotations (@Nonnull, @Nullable)
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.google.code.gson:gson:2.10.1")

    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Jar> {
    destinationDirectory.set(file("/home/sully/Development/Hytale/Servers/1.28-server/Server/mods/"))

    // Optional: configure the jar file name
    archiveFileName.set("ezchat-1.0.0.jar")
}

tasks.test {
    useJUnitPlatform()
}