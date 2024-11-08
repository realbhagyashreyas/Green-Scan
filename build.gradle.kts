// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral() // Ensure this is included
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.0") // or latest version
        classpath ("com.google.gms:google-services:4.3.8") // Firebase setup
    }
}

