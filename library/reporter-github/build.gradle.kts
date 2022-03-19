@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-jvm")
    id("convention.publish-kotlin-library")
}

dependencies {
    api(libs.kotlinCoroutines)
    api(projects.core)
    api(projects.reporterApi)
}