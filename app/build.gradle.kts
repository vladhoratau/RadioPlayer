plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
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

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation(libs.material)
    implementation("net.adamcin.httpsig:httpsig-api:1.3.1")
    implementation("net.adamcin.httpsig:httpsig-bouncycastle:0.6.0")
    implementation("net.adamcin.httpsig:httpsig-ssh-jce:1.3.1")
    implementation("org.bouncycastle:bcpkix-fips:1.0.3")
    implementation(libs.firebase.crashlytics.buildtools)
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("org.slf4j:slf4j-api:1.7.32") // Add SLF4J API dependency
    implementation("org.slf4j:slf4j-simple:1.7.32") // Add SLF4J Simple binding
}