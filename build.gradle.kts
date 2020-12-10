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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

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
    mainClassName = "day10.Day10Kt"
}