plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform")
    id("dev.icerock.mobile.multiplatform-resources")
    id("kotlinx-serialization")
    id("kotlin-android-extensions")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("Server") {
        packageName = "net.compoza.lite2"
    }
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
    }
}

val mppLibs = listOf(
    Deps.Libs.MultiPlatform.mokoResources,
    Deps.Libs.MultiPlatform.mokoWidgets
)

setupFramework(
    exports = mppLibs
)

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClient)
    mppLibrary(Deps.Libs.MultiPlatform.serialization)
    mppLibrary(Deps.Libs.MultiPlatform.lifecycle)
    mppLibrary(Deps.Libs.MultiPlatform.sqldelight)

    mppLibs.forEach { mppLibrary(it) }
}

multiplatformResources {
    multiplatformResourcesPackage = "net.compoza.lite2.library"
}