@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

group = "com.lounah.kotlin-binary-reporter.buildconventions"

dependencies {
    implementation(libs.kotlinGradle)
    implementation("com.lounah.kotlin-binary-reporter.buildconventions:util")
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
