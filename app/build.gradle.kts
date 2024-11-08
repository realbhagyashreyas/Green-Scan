plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.ecofriendlyscanner"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ecofriendlyscanner"
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


}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Barcode scanning libraries
    implementation("com.journeyapps:zxing-android-embedded:4.3.0") // Barcode scanning library
    implementation("com.google.zxing:core:3.4.1") // ZXing core library
    implementation("androidx.activity:activity-ktx:1.6.1") // Updated ActivityResult API version
    implementation("androidx.appcompat:appcompat:1.6.1") // Updated AppCompatActivity version
    implementation("androidx.core:core-ktx:1.10.0") // Updated Core KTX version
    implementation("com.google.android.material:material:1.10.0") // Material Design components
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit for API calls
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson converter for Retrofit
    implementation("com.journeyapps:zxing-android-embedded:4.3.0") // Barcode scanning library
    implementation("com.google.zxing:core:3.4.1") // Core library for barcode scanning
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

}
