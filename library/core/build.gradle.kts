@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-jvm")
}

dependencies {
    implementation(projects.library.descriptor)
    implementation(libs.kotlinBinaryCompatibility)
    implementation(libs.asm)
    implementation(libs.asmTree)
}
