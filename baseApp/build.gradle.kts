
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {

    compileSdk = 31
    defaultConfig {
        minSdk = 21
        targetSdk = 31
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }


    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {


    api("androidx.core:core-ktx:1.7.0")
    api( "androidx.appcompat:appcompat:1.4.1")
    api( "com.google.android.material:material:1.5.0")
    api("androidx.constraintlayout:constraintlayout:2.1.3")
    api("androidx.legacy:legacy-support-v4:1.0.0")
    api("androidx.multidex:multidex:2.0.1")
    api( "junit:junit:4.13.2")
    api( "androidx.test.ext:junit:1.1.3")
    api("androidx.test.espresso:espresso-core:3.4.0")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    api( "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    api("androidx.navigation:navigation-fragment-ktx:2.4.1")
    api("androidx.navigation:navigation-ui-ktx:2.4.1")

    api("androidx.datastore:datastore-preferences:1.0.0")

    api("com.google.code.gson:gson:2.8.9")
    api("com.squareup.okhttp3:logging-interceptor:4.9.1")
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")


    api("com.github.bumptech.glide:glide:4.13.0")
    api("id.zelory:compressor:3.0.1")
    api( "com.airbnb.android:lottie:5.0.2")
    api("com.intuit.sdp:sdp-android:1.0.6")
    api("com.intuit.ssp:ssp-android:1.0.6")


}