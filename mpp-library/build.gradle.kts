plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform")
    id("dev.icerock.mobile.multiplatform-resources")
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
    Deps.Libs.MultiPlatform.mokoWidgets,
    Deps.Libs.MultiPlatform.multiplatformSettings
)

setupFramework(
    exports = mppLibs
)

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)
    mppLibrary(Deps.Libs.MultiPlatform.ktorClient)
    mppLibrary(Deps.Libs.MultiPlatform.multiplatformSettings)

    androidLibrary(Deps.Libs.Android.lifecycle)

    mppLibs.forEach { mppLibrary(it) }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library"
}