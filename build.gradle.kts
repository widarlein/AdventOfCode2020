plugins {
    kotlin("jvm") version "1.4.0"
    application
}

group = "nu.widar"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}

application {
    mainClassName = "day6.Day6Kt"
}