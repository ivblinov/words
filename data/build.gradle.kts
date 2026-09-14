import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "io.github.ivblinov.words.data"
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
    implementation(project(":domain"))

    // core:
    implementation(libs.androidx.core.ktx)

    // room:
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // hilt:
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    // test:
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}