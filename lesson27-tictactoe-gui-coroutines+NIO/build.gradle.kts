plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.compose") version "1.2.1"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(project(":lesson09-tictactoe-model"))
    implementation("io.ktor:ktor-client-core:2.1.3")
    implementation("io.ktor:ktor-client-cio:2.1.3")

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

compose.desktop {
    application {
        mainClass = "pt.isel.AppKt"
    }
}
