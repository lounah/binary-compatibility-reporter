@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-jvm")
}

dependencies {
    api(projects.library.core)
    api(projects.library.reporterApi)
}