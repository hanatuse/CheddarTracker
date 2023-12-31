plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.cheddartracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cheddartracker"
        minSdk = 24
        targetSdk = 33
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
}

//repositories {
//    maven {
//        url "https://jitpack.io" }
//}

dependencies {

    //noinspection GradleCompatible,GradleCompatible
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("io.reactivex.rxjava2:rxandroid:2.0.1")
    implementation ("io.reactivex.rxjava2:rxjava:2.0.8")
    implementation ("com.google.code.gson:gson:2.10")

}