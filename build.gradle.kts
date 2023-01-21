import com.google.wireless.android.sdk.stats.GradleBuildProject.PluginType

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
//    plugins{
//        id("io.gitlab.arturbosch.detekt").version("1.20.0-RC1").apply(false)
//    }
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath(Deps.AppPlugins.android_gradle)
        classpath(Deps.AppPlugins.kotlin)
        classpath(Deps.AppPlugins.hilt)
        classpath(Deps.AppPlugins.gms)
        classpath(Deps.AppPlugins.Firebase.crashlytics)
        classpath(Deps.AppPlugins.Firebase.performance)
        classpath(Deps.AppPlugins.Firebase.app_distribution)
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath(Deps.AppPlugins.Github.github_analysis)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    configurations.all {
        resolutionStrategy.eachDependency {
            when {
                requested.name.startsWith("kotlin-stdlib") -> {
                    useTarget(
                        "${requested.group}:${requested.name.replace(
                            "jre",
                            "jdk"
                        )}:${requested.version}"
                    )
                }
                else -> when (requested.group) {
                    "org.jetbrains.kotlin" -> useVersion(Deps.Versions.kotlin)
                }
            }
        }
    }
}

tasks.register("clean").configure {
    delete("build")
}
