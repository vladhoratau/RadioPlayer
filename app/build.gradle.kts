plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.5.21"
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.radioplayer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.radioplayer"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.appcompat.v140)
    implementation(libs.material)
    implementation(libs.httpsig.api)
    implementation(libs.httpsig.bouncycastle)
    implementation(libs.httpsig.ssh.jce)
    implementation(libs.bcpkix.fips)
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(libs.okhttp)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.slf4j.api) // Add SLF4J API dependency
    implementation(libs.slf4j.simple) // Add SLF4J Simple binding

    //Coroutines
    implementation(libs.bundles.kotlin.coroutines)

    //Retrofit
    implementation(libs.bundles.squareup.retrofit)

    //Hilt
    implementation(libs.androidx.hilt)
    kapt(libs.dagger.hilt.compiler)

    //Navigation
    implementation(libs.bundles.navigation)
}