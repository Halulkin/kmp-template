plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinCompose)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose
                implementation(compose.runtime)
                implementation(compose.foundation)
                // Ktor
                api(libs.ktor.core)
                api(libs.ktor.serialization.kotlinx.json)
                api(libs.ktor.client.content.negotiation)
                api(libs.ktor.client.logging)
                // Kotlin
                api(libs.kotlin.serialization.json)
                api(libs.kotlin.coroutines.core)
                // Koin
                api(libs.koin.core)
                // MOKO - MVVM
                api(libs.moko.mvvm.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                // Ktor
                implementation(libs.ktor.client.android)
                // Kotlin
                implementation(libs.kotlin.coroutines.android)
                // Koin
                api(libs.koin.android)
                api(libs.koin.compose)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                // Ktor
                implementation(libs.ktor.client.ios)
            }
        }
    }
}

android {
    namespace = "com.monstarlab.kmptemplate"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
