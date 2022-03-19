@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-jvm")
    id("convention.publish-kotlin-library")
}

dependencies {
    api(libs.kotlinBinaryCompatibility)
    implementation(projects.descriptor)
    implementation(libs.asm)
    implementation(libs.asmTree)
}
