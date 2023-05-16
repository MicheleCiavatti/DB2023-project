plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.14"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    implementation("mysql:mysql-connector-java:8.0.29")
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("view.Menu")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
