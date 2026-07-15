plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "io.github.ivblinov.words.core.resources"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }
}