@file:Suppress("UnstableApiUsage")

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "kotlin-binary-compatibility-reporter"

pluginManagement {
    includeBuild("build-settings")
}

plugins {
    id("convention-dependencies")
    id("convention-plugins")
}

includeBuild("build-conventions")
includeBuild("sample")

include(":library:core")
include(":library:descriptor")
include(":library:reporter-api")
include(":library:reporter-gitlab")