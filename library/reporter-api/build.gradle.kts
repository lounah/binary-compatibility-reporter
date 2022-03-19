@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-jvm")
    id("convention.publish-kotlin-library")
}

dependencies {
    api(libs.retrofit)
    api(libs.okhttpLogging)
}