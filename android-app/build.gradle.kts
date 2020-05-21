plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dev.icerock.mobile.multiplatform-units")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    dexOptions {
        javaMaxHeapSize = "2g"
    }

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)

        applicationId = "org.example.app" // REPLACE:org.example.app:APP_ID

        versionCode = 1
        versionName = "1.0"

        vectorDrawables.useSupportLibrary = true

        val url = "https://newsapi.org/v2/"
        buildConfigField("String", "BASE_URL", "\"$url\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(Deps.Libs.Android.kotlinStdLib.name)

    implementation(Deps.Libs.Android.appCompat.name)
    implementation(Deps.Libs.Android.material.name)
    implementation(Deps.Libs.Android.recyclerView.name)

    implementation(Deps.Libs.MultiPlatform.napier.android!!)

    implementation(project(":mpp-library"))
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
}

multiplatformUnits {
    classesPackage = "org.example.app" // REPLACE:org.example.app:APP_ID
    dataBindingPackage = "org.example.app" // REPLACE:org.example.app:APP_ID
    layoutsSourceSet = "main"
}
