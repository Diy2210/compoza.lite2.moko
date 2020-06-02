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
    database("Server") {
        packageName = "org.example.mpp"
        sourceFolders = listOf("sqldelight")
    }

//    database("ServerDB") {
//        packageName = "org.example.app"
//        sourceFolders = (listOf("sqldelight"))
//        schemaOutputDirectory = file("src/commonMain/sqldelight/databases")
//    }

//    packageName = "org.example.mpp"
//    className = "Database"
//    sourceSet = files("src/commonMain/kotlin")
//    schemaOutputDirectory = file("src/commonMain/kotlin")
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


    androidLibrary(Deps.Libs.Android.lifecycle)

    mppLibs.forEach { mppLibrary(it) }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library"
}