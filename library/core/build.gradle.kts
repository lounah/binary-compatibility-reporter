@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-jvm")
}

dependencies {
    api(libs.kotlinBinaryCompatibility)
    implementation(projects.library.descriptor)
    implementation(libs.asm)
    implementation(libs.asmTree)
}
