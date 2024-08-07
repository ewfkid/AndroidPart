buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath (libs.google.services)
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    id ("com.android.library") version "7.4.1" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}