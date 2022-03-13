@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-jvm")
}

dependencies {
    api(libs.kotlinCoroutines)
    api(projects.library.core)
    api(projects.library.reporterApi)
}