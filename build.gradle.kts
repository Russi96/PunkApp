// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version Version.gradleVersion apply false
    id ("com.android.library") version Version.gradleVersion apply false
    id ("org.jetbrains.kotlin.android") version Version.kotlin apply false
    id("androidx.navigation.safeargs.kotlin") version Version.navigation apply false
}

buildscript {
   
    dependencies {
       
        classpath ("com.google.dagger:hilt-android-gradle-plugin:${Version.dagger}")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}