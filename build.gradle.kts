buildscript {

    val kotlinVersion by extra{ "1.6.21" } // from 1.5.31
    val dokkaVersion by extra{ "1.6.21" } // from 1.5.31
    val composeVersion by extra { "1.2.0-beta03" }
    val hiltVersion by extra { "2.42" }

    repositories {
        google()
        mavenCentral()
    }

    dependencies {

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        classpath ("com.google.gms:google-services:4.3.13")
        classpath ("com.android.tools.build:gradle:7.1.3") //changed from 2 to 4
        classpath ("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion" )
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${dokkaVersion}")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion" )
        classpath ("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion" )
//        classpath ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")

    }
}

tasks.register(name = "type",type = Delete::class){
    delete(rootProject.buildDir)
}