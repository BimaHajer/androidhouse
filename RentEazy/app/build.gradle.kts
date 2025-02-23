plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.renteazy"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.renteazy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("androidx.compose.material3:material3:1.0.0")
    implementation ("androidx.compose.ui:ui:1.4.0")
    implementation ("androidx.compose.foundation:foundation:1.4.0")
    implementation ("androidx.compose.material3:material3:1.1.0")
    implementation ("androidx.compose.foundation:foundation:1.5.0")

    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation ("androidx.compose.ui:ui:1.6.0")
    implementation ("com.google.accompanist:accompanist-permissions:0.31.1-alpha")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.31.1-alpha")
    implementation ("androidx.compose.ui:ui:1.3.0")
    implementation ("androidx.compose.material3:material3:1.0.1")
    implementation ("androidx.compose.material:material-icons-extended:1.3.0")
    implementation( "androidx.activity:activity-compose:1.6.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.3.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.3.0")
    implementation ("io.coil-kt:coil-compose:2.4.0") // Version de Coil
    implementation ("io.coil-kt:coil-gif:2.4.0") // Module GIF pour Coil
    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation ("androidx.compose.ui:ui:1.5.4")
    implementation ("androidx.compose.foundation:foundation:1.5.4")


    implementation("androidx.compose.material3:material3:1.2.0")
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.core)
    val composeBom = platform("androidx.compose:compose-bom:2024.10.01")
        implementation(composeBom)
        androidTestImplementation(composeBom)
        // Choose one of the following:
        // Material Design 3
        implementation("androidx.compose.material3:material3")
        // or Material Design 2
        implementation("androidx.compose.material:material")
        // or skip Material Design and build directly on top of foundational components
        implementation("androidx.compose.foundation:foundation")
        // or only import the main APIs for the underlying toolkit systems,
        // such as input and measurement/layout
        implementation("androidx.compose.ui:ui")

        // Android Studio Preview support
        implementation("androidx.compose.ui:ui-tooling-preview")
        debugImplementation("androidx.compose.ui:ui-tooling")

        // UI Tests
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-test-manifest")

        // Optional - Included automatically by material, only add when you need
        // the icons but not the material library (e.g. when using Material3 or a
        // custom design system based on Foundation)
        implementation("androidx.compose.material:material-icons-core")
        // Optional - Add full set of material icons
        implementation("androidx.compose.material:material-icons-extended")
        // Optional - Add window size utils
        implementation("androidx.compose.material3.adaptive:adaptive")

        // Optional - Integration with activities
        implementation("androidx.activity:activity-compose:1.9.2")
        // Optional - Integration with ViewModels
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
        // Optional - Integration with LiveData
        implementation("androidx.compose.runtime:runtime-livedata")
        // Optional - Integration with RxJava
        implementation("androidx.compose.runtime:runtime-rxjava2")

    implementation("io.coil-kt:coil-compose:2.0.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}