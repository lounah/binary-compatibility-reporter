@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-jvm")
}

dependencies {
    compileOnly(projects.library.core)
    api(libs.kotlinCoroutines)
    api(libs.retrofit)
    api(libs.okhttpLogging)
}