val composeVersion = "1.5.0" // Use a versão mais recente disponível baseada na sua versão do Kotlin.

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")


}

android {
    namespace = "com.example.examine_ai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.examine_ai"
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }


}

dependencies {

    //noinspection GradleDependency
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.camera:camera-lifecycle:1.2.3")
    implementation("androidx.camera:camera-core:1.2.3")
    implementation("androidx.camera:camera-camera2:1.2.3")
    implementation("androidx.camera:camera-view:1.2.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.firebase:firebase-ml-vision:24.0.1")
    implementation("androidx.room:room-runtime:2.4.1")
    ksp("androidx.room:room-compiler:2.4.1")
    implementation("androidx.room:room-ktx:2.4.1")
    implementation("com.google.code.gson:gson:2.8.8")

    //Compose
        implementation("androidx.compose.ui:ui:$composeVersion")
        implementation("androidx.compose.material:material:$composeVersion")
        implementation("androidx.compose.ui:ui-tooling:$composeVersion")
        implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
        implementation("androidx.compose.runtime:runtime-rxjava2:$composeVersion")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
        implementation("androidx.activity:activity-compose:1.3.0")
        implementation("androidx.navigation:navigation-compose:2.4.0-alpha08")
        implementation("androidx.concurrent:concurrent-futures-ktx:1.1.0")
        implementation("androidx.compose.material3:material3:1.2.0-alpha02")



    // UI Tests:
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

        // ... outras dependências





}