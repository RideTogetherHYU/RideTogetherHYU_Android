plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.taxi.sharing_public_taxi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.taxi.sharing_public_taxi"
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
    buildFeatures{
        viewBinding = true
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
    // Navigation 라이브러리
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.2")
    // Core AndroidX 및 UI 라이브러리
    implementation(libs.androidx.core.ktx)         // androidx.core:core-ktx
    implementation(libs.androidx.appcompat)        // androidx.appcompat:appcompat
    implementation(libs.material)                 // com.google.android.material:material
    implementation(libs.androidx.activity)        // androidx.activity:activity
    implementation(libs.androidx.constraintlayout) // androidx.constraintlayout:constraintlayout

    // JUnit 테스트
    testImplementation(libs.junit)                // junit:junit

    // Android 테스트 (Instrumentation)
    androidTestImplementation(libs.androidx.junit) // androidx.test.ext:junit
    androidTestImplementation(libs.androidx.espresso.core) // androidx.test.espresso:espresso-core

    // 명시적인 테스트 의존성
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}