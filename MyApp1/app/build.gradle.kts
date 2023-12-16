
plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.john.portfolio"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.john.portfolio"
        minSdk = 21
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.fragment:fragment-ktx:1.6.1")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.3")

    //room
    implementation ("androidx.room:room-runtime:2.5.2")
    implementation ("androidx.room:room-ktx:2.5.2")
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation ("androidx.paging:paging-runtime:3.2.1")
    kapt("androidx.room:room-compiler:2.5.2")

    implementation ("androidx.viewpager:viewpager:1.0.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.1")
    implementation ("androidx.paging:paging-common-ktx:3.2.1")
    implementation ("androidx.paging:paging-common:3.2.1")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation ("com.github.bumptech.glide:glide:4.14.2")
    kapt ("com.github.bumptech.glide:compiler:4.14.2")

    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation ("com.airbnb.android:lottie:6.0.0")

    //dagger-hilt
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
}
