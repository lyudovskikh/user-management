import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.30"
    kotlin("kapt") version "1.5.30"
    application
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "com.lyudovskikh"
version = "1.0-SNAPSHOT"

val mainMethodClass = "com.lyudovskikh.user.management.UserManagementApplicationKt"

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<ShadowJar> {
    mergeServiceFiles()
    exclude("META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.SF")
    manifest {
        attributes["Main-Class"] = mainMethodClass
    }
    archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.5.30")
    implementation("io.dropwizard:dropwizard-core:2.0.25")
    implementation("io.dropwizard:dropwizard-jdbi3:2.0.25")
    implementation("io.dropwizard:dropwizard-migrations:2.0.25")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.5")
    implementation("org.jdbi:jdbi3-kotlin:3.21.0")
    implementation("org.jdbi:jdbi3-kotlin-sqlobject:3.21.0")
    implementation("org.kodein.di:kodein-di:7.8.0")
    implementation("com.smoketurner:dropwizard-swagger:2.0.0-1")
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")
    runtimeOnly("com.h2database:h2:1.4.200")

    testImplementation("io.dropwizard:dropwizard-testing:2.0.25")
    testImplementation("org.mockito:mockito-inline:3.12.0")
}

application {
    mainClass.set(mainMethodClass)
}

(tasks.run) {
    args = listOf("server", "config/local.yaml")
}