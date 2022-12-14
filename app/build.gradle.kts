plugins {
//    id("com.android.library")

    id("com.android.application")
    id("kotlin-android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.dokka")
//    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

val kotlinVersion : String by rootProject.extra
val hiltVersion : String by rootProject.extra
val composeVersion : String by rootProject.extra
//val dokka_version : String by rootProject.extra
val roomVersion = "2.4.2"
//val ktor_version = "1.5.3"
val activityVersion = "1.3.1"
val lifecycleVersion = "2.5.0"
val accompanistVersion = "0.24.10-beta"

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
        kotlinCompilerExtensionVersion = composeVersion
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
    implementation ("com.google.accompanist:accompanist-pager:${accompanistVersion}")
    implementation ("com.google.accompanist:accompanist-pager-indicators:${accompanistVersion}")
    implementation ("com.google.accompanist:accompanist-navigation-animation:${accompanistVersion}")
    implementation ("com.google.accompanist:accompanist-webview:${accompanistVersion}")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:${accompanistVersion}")

//  Coil for image loading
//  implementation("io.coil-kt:coil-compose:2.0.0-rc01")

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
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")

    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:$composeVersion")

    // Material Design
    implementation("androidx.compose.material:material:$composeVersion")
    implementation ("androidx.compose.material3:material3:1.0.0-alpha13")

    // Material design icons
    implementation("androidx.compose.material:material-icons-core:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")

    // Integration with activities
    implementation("androidx.activity:activity-compose:1.5.0-rc01")
    implementation("androidx.activity:activity-ktx:$activityVersion")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("androidx.compose.runtime:runtime-rxjava2:$composeVersion")

    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation ("androidx.compose.ui:ui-tooling:$composeVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

    //Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")


    // Hilt Jetpack Integrations
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")

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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=org.mylibrary.OptInAnnotation"
}

// Creates the KDOC documentation in HTML
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

// Creates the KDOC documentation in Markdown
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
}
