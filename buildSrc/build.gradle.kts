plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()

    maven { url = uri("https://dl.bintray.com/icerockdev/plugins") }
}

dependencies {
    implementation("dev.icerock:mobile-multiplatform:0.6.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    implementation("com.android.tools.build:gradle:3.6.3")
    implementation("com.squareup.sqldelight:gradle-plugin:1.3.0")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
