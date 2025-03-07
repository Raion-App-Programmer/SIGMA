plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.login"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.login"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM (Bill of Materials) to manage Compose versions
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Material 3 - use the latest stable version consistently
    implementation("androidx.compose.material3:material3:1.1.2")

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.androidx.storage)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.paging.common.android)
    implementation(libs.generativeai)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth:23.2.0")
    implementation("androidx.credentials:credentials:1.5.0-rc01")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // Compose Material - use consistent versions
    implementation("androidx.compose.material:material:1.3.0")
    implementation("androidx.compose.material:material-icons-extended:1.5.1")

    // Remove duplicate or conflicting Material 3 dependencies
    // implementation("androidx.compose.material3:material3:<version>")
    // implementation("androidx.compose.material3:material3:1.0.0")

    // Firebase Storage
    implementation("com.google.firebase:firebase-firestore-ktx:24.9.1")

    // firebase messaging
    implementation("com.google.firebase:firebase-messaging-ktx")

    // Compose Foundation - use consistent versions
    implementation("androidx.compose.foundation:foundation:1.4.3")

    // Compose UI - use consistent versions
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-text:1.4.3")

    // Accompanist Pager
    implementation("com.google.accompanist:accompanist-pager:0.28.0")

    // Coil Image Loading
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")

    // Navigation Compose
    val nav_version = "2.8.8"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // material
    implementation( "androidx.compose.material:material-icons-extended:1.5.0")

    // media compose
    implementation("androidx.media3:media3-exoplayer:1.2.0")
    implementation("androidx.media3:media3-ui:1.2.0")
}
