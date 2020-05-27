plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform")
    id("dev.icerock.mobile.multiplatform-resources")
    id("com.squareup.sqldelight")
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
    }
}

sqldelight {
    database("ServerDB") {
        packageName = "org.example.app"
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

    androidLibrary(Deps.Libs.Android.lifecycle)

    mppLibs.forEach { mppLibrary(it) }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library"
}
