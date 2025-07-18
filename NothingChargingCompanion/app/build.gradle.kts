plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "com.divyam.nothingchargingcompanion"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.divyam.nothingchargingcompanion"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
}
