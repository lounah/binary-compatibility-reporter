@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-jvm")
}

dependencies {
    api(libs.kotlinCoroutines)
    api(libs.retrofit)
    api(libs.okhttpLogging)
}