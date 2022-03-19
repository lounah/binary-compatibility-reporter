@file:Suppress("UnstableApiUsage")

rootProject.name = "library"

pluginManagement {
    includeBuild("../build-settings")
}

plugins {
    id("convention-dependencies")
    id("convention-plugins")
}

includeBuild("../build-conventions")

include(":core")
include(":descriptor")
include(":reporter-api")
include(":reporter-bitbucket")
include(":reporter-github")
include(":reporter-gitlab")
include(":plugin")
