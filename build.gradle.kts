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

//tasks {
//    "test"(Test::class) {
//        useJUnitPlatform()
//    }
//}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClassName = "day9.Day9Kt"
}