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
    database("ServerBase") {
        packageName = "org.example.mpp"
        sourceFolders = listOf("sqldelight")
        schemaOutputDirectory = file("build/dbs")
    }
//    linkSqlite = false
}

val mppLibs = listOf(
    Deps.Libs.MultiPlatform.mokoResources,
    Deps.Libs.MultiPlatform.mokoWidgets
//    Deps.Libs.MultiPlatform.mokoNetwork
)

setupFramework(
    exports = mppLibs
)

dependencies {
    mppLibrary(Deps.Libs.MultiPlatform.kotlinStdLib)
    mppLibrary(Deps.Libs.MultiPlatform.coroutines)

    mppLibrary(Deps.Libs.MultiPlatform.ktorClient)

    androidLibrary(Deps.Libs.Android.lifecycle)

    mppLibs.forEach { mppLibrary(it) }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library"
}