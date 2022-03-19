@file:Suppress("UnstableApiUsage")

rootProject.name = "sample"

pluginManagement {
    includeBuild("../build-settings")
}

plugins {
    id("convention-dependencies")
    id("convention-plugins")
}

includeBuild("../build-conventions")
includeBuild("../library")

include(":root-project")
include(":common-project")