repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
