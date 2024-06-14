plugins {
    alias(libs.plugins.android.application) // Bu satırı koruyorum
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.safeArgs)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.mobven.fitai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mobven.fitai"
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
        viewBinding = true
        buildConfig = true
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.bundles.testImplementation)
    androidTestImplementation(libs.bundles.androidTestImplementation)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    // Navigation
    implementation(libs.bundles.navigation)

    // Viewpager Indicator - 3rd Party
    implementation(libs.thirdparty.viewpagerdotsindicator)

    // Hilt
    implementation(libs.bundles.hiltImplementation)
    kapt(libs.hilt.compiler)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // OkHttp
    implementation(libs.bundles.http)

    // Room
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    // Lottie
    implementation(libs.lottie)

    // Gson
    implementation(libs.gson)

}
