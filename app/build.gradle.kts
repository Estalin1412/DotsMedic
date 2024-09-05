plugins {
    //Para Agregar google services
    id("com.google.gms.google-services")
    /*Para crashlytics----------------------------------------------------------------------*/
    // Add the Crashlytics Gradle plugin
    id("com.google.firebase.crashlytics")

    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

}

android {
    namespace = "com.Dmedic.dotsmedic"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.Dmedic.dotsmedic"
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
    //Para importar fireBase
    implementation(platform("com.google.firebase:firebase-bom:33.2.0"))
    implementation("com.google.firebase:firebase-analytics")
    //Sincronizar Jsdon con Gradle a nivel app
    implementation("com.google.code.gson:gson:2.8.6")
    //Para MQTT
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("mx.electronica117:Android-MQTT:1.2")
    //Para crashlitycs---------------------------------------------------------
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    // Add the dependencies for the Crashlytics and Analytics libraries
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.database.ktx)
    implementation(libs.firebase.common.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}