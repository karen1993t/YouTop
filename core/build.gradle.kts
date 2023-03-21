plugins {
    id("com.android.library")
    id(Dependencies.Plugins.kotlinPlugin)
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.1"
    }
}

dependencies {

    implementation(Dependencies.Android.ktxCore)
    implementation(Dependencies.Android.runtimeLifecycle)
    testImplementation(Dependencies.Android.jUnit)
    androidTestImplementation(Dependencies.Android.jUnitTest)
    androidTestImplementation(Dependencies.Android.espresso)

    //Compose
    implementation(Dependencies.Compose.activityCompose)
    androidTestImplementation(Dependencies.Compose.composeJUnit)
    debugImplementation(Dependencies.Compose.composeUITooling)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.preview)
    implementation(Dependencies.Compose.composeFoundation)
    implementation(Dependencies.Compose.composeMaterialIcons)
    implementation(Dependencies.Compose.composeMaterialIconsExtended)
    implementation(Dependencies.Compose.composeMaterial3)
    implementation(Dependencies.Compose.composeConstraintLayout)
    implementation(Dependencies.Compose.permissions)

    // Navigation Compose
    implementation(Dependencies.Compose.composeNavigation)
    implementation(Dependencies.Compose.accompanistPermissions)
    implementation(Dependencies.Compose.accompanist)
    implementation(Dependencies.Compose.accompanistAnimationNavigation)

     // Koin main features for Android
    implementation(Dependencies.Koin.koinAndroid)
    implementation(Dependencies.Koin.koinCompose)
    implementation(Dependencies.Koin.koinCore)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutinesAndroid)
    implementation(Dependencies.Coroutines.coroutinesCore)

  // Timber
    implementation(Dependencies.Timber.timber)
}