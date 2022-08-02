plugins {
//    id("com.android.library")

    id("com.android.application")
    id("kotlin-android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
//    id("org.jetbrains.dokka")
//    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

val kotlin_version : String by rootProject.extra
val hilt_version : String by rootProject.extra
val compose_version : String by rootProject.extra
//val dokka_version : String by rootProject.extra
val roomVersion = "2.4.2"
//val ktor_version = "1.5.3"
val activity_version = "1.3.1"
val lifecycle_version = "2.5.0"
val accompanist_version = "0.24.10-beta"

android {
    compileSdk = 32
    buildToolsVersion =  "30.0.3"

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

//    dokkaGfmPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:${dokka_version}")
    implementation ("androidx.core:core-ktx:1.8.0") // from 1.7.0
    implementation ("androidx.appcompat:appcompat:1.4.2") // from 1.4.1
    implementation ("com.google.android.material:material:1.7.0-alpha02")

    // Accompanist by Google
    implementation ("com.google.accompanist:accompanist-pager:${accompanist_version}")
    implementation ("com.google.accompanist:accompanist-pager-indicators:${accompanist_version}")
    implementation ("com.google.accompanist:accompanist-navigation-animation:${accompanist_version}")
    implementation ("com.google.accompanist:accompanist-webview:${accompanist_version}")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:${accompanist_version}")

    //Coil for image loading
//    implementation("io.coil-kt:coil-compose:2.0.0-rc01")

    // Google Maps
    implementation("com.google.maps.android:maps-compose:1.0.0")
    implementation("com.google.android.gms:play-services-maps:18.0.2")

    // Serialization (Json -> Class, Class -> Json)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:30.1.0"))
    implementation("com.google.firebase:firebase-messaging")
    // implementation("com.google.firebase:firebase-analytics")

    // Api Client Ktor
//    implementation("io.ktor:ktor-client-android:${ktor_version}")
//    implementation("io.ktor:ktor-client-serialization:${ktor_version}")
//    implementation("io.ktor:ktor-client-logging-jvm:${ktor_version}")

    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:$compose_version")

    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:$compose_version")

    // Material Design
    implementation("androidx.compose.material:material:$compose_version")
    implementation ("androidx.compose.material3:material3:1.0.0-alpha13")

    // Material design icons
    implementation("androidx.compose.material:material-icons-core:$compose_version")
    implementation("androidx.compose.material:material-icons-extended:$compose_version")

    // Integration with activities
    implementation("androidx.activity:activity-compose:1.5.0-rc01")
    implementation("androidx.activity:activity-ktx:$activity_version")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:$compose_version")
    implementation("androidx.compose.runtime:runtime-rxjava2:$compose_version")

    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    debugImplementation ("androidx.compose.ui:ui-tooling:$compose_version")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")

    //Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")


    // Hilt Jetpack Integrations
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")

    //Jetpack compose Navigation Feature
    implementation("androidx.navigation:navigation-compose:2.4.0")

    // Room components
    implementation("androidx.room:room-ktx:${roomVersion}")
    androidTestImplementation("androidx.room:room-testing:${roomVersion}")
    kapt("androidx.room:room-compiler:${roomVersion}")
    api("androidx.room:room-runtime:${roomVersion}")
    //implementation("androidx.room:room-runtime:${roomVersion}")
    kapt("androidx.room:room-compiler:${roomVersion}")

    //Security (Encrypted Shared Preferences)
//    implementation("androidx.security:security-crypto:1.0.0")
//    implementation("androidx.security:security-identity-credential:1.0.0-alpha03")

    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    //ExoPlayer to Play HLS in Dashboard. Remove if its not necessary
//    implementation("com.google.android.exoplayer:exoplayer:2.17.0")
}

kapt {
    correctErrorTypes = true
}

/*
tasks.named<org.jetbrains.dokka.gradle.DokkaTask>("dokkaHtml").configure {
    // outputDirectory.set(buildDir.resolve("dokka"))
    suppressInheritedMembers.set(true)
    dokkaSourceSets {
        named("main") {
            noAndroidSdkLink.set(false)
        }
        configureEach {
            skipEmptyPackages.set(true)
        }
    }
}


tasks.named<org.jetbrains.dokka.gradle.DokkaTask>("dokkaGfm").configure {
    // outputDirectory.set(buildDir.resolve("dokka"))
    suppressInheritedMembers.set(true)
    dokkaSourceSets {
        named("main") {
            noAndroidSdkLink.set(false)
        }
        configureEach {
            skipEmptyPackages.set(true)
        }
    }
}*/
