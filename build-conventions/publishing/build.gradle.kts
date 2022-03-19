@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

group = "com.lounah.kt-binary-reporter.buildconventions"

dependencies {
    implementation(libs.kotlinGradle)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
