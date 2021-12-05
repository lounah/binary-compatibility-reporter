@file:Suppress("UnstableApiUsage")

rootProject.name = "build-conventions"

pluginManagement {
    includeBuild("../build-settings")
}

plugins {
    id("convention-plugins")
    id("convention-dependencies")
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

//
include("kotlin")
include("testing")
include("util")
//include("checks")
//include("publishing")