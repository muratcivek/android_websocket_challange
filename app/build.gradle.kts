plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.android_websocket_challange"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.android_websocket_challange"
        minSdk = 26
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    viewBinding {
        buildFeatures {
            viewBinding = true
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Jetpack Compose bağımlılıklarını kaldırdık
    // implementation(libs.androidx.ui)
    // implementation(libs.androidx.ui.graphics)
    // implementation(libs.androidx.ui.tooling.preview)

   // implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation ("androidx.activity:activity-ktx:1.7.0")

    // OkHttp için gerekli bağımlılıklar
    implementation("com.squareup.okhttp3:okhttp:4.10.0") // OkHttp ana bağımlılığı
    implementation("com.squareup.okhttp3:okhttp-urlconnection:4.10.0") // Eğer URLConnection kullanıyorsanız
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0") // İsteğe bağlı olarak logging için

    // Okio, OkHttp ile birlikte gelir, fakat bazı durumlarda elle eklemek gerekebilir
    implementation("com.squareup.okio:okio:3.2.0")

    // Gson bağımlılığı
    implementation("com.google.code.gson:gson:2.8.8")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")  // En son sürüm
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")   // En son sürüm

}
