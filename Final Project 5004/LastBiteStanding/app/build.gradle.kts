plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }



    namespace = "com.lastbite.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lastbite.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.play.services.location)
    implementation(libs.play.services.maps)


    implementation(libs.places.v270)

    implementation(libs.places.v330)
    implementation(libs.play.services.maps.v1820)

    implementation(libs.androidx.cardview)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.core.ktx)






}