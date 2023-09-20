plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("dk.nstack.translation.plugin")
}

val nStackKey = "LqWLm621BwIxNRzdrei88pKhIIEI2EE8ni8r"
val nStackAppId = "IXmpT4N7MJbGEXvDfGqGH4UKHrmV0EOqFeK0"

translation {
    appId = nStackAppId
    apiKey = nStackKey
    acceptHeader = "en-GB"
}

android {
    namespace = "com.monstarlab.kmptemplate.android"
    compileSdk = 34
    flavorDimensions += "default"
    defaultConfig {
        applicationId = "com.monstarlab.kmptemplate.android"
        manifestPlaceholders += mapOf("appId" to nStackAppId, "apiKey" to nStackKey)
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    productFlavors {
        create("dev") {
            manifestPlaceholders += mapOf("APP_NAME" to "MonstarlabDev", "env" to "staging")
            dimension = "default"
            applicationIdSuffix = ".dev"
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
        create("staging") {
            manifestPlaceholders += mapOf("APP_NAME" to "MonstarlabStaging", "env" to "staging")
            dimension = "default"
            applicationIdSuffix = ".staging"
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
        create("production") {
            manifestPlaceholders += mapOf("APP_NAME" to "Monstarlab", "env" to "production")
            dimension = "default"
            applicationIdSuffix = ".staging"
            //signingConfig signingConfigs.production
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
    }
}

dependencies {
    implementation(projects.shared)

    // Android
    implementation(libs.android.splash)
    // Koin
    implementation(libs.koin.android)
    // Other
    implementation(libs.nstack)

    // Compose
    implementation(platform(libs.android.compose.bom))
    implementation(libs.android.compose.ui)
    implementation(libs.android.compose.material3)
    implementation(libs.android.compose.foundation)
    implementation(libs.android.activity.compose)
//    implementation(libs.bundles.android.lifecycle)


//    implementation(libs.koin.core)
//    implementation(libs.koin.annotations)
//    ksp(libs.koin.ksp.compiler)
}