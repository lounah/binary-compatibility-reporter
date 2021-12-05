@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

group = "com.lounah.kotlin-binary-reporter.buildconventions"

dependencies {
    implementation(libs.kotlinGradle)
    implementation("com.lounah.kotlin-binary-reporter.buildconventions:testing")
    // workaround for https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
