@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-jvm")
    id("convention.publish-kotlin-library")
}

dependencies {
    api(libs.kotlinCoroutines)
    api(projects.library.core)
    api(projects.library.reporterApi)
}