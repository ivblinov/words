import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "io.github.ivblinov.words.core.database"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

dependencies {

    // project:
    implementation(project(":data"))

    // core:
    implementation(libs.androidx.core.ktx)

    // hilt:
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    // room:
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // test:
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}