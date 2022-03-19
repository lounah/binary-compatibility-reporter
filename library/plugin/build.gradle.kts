@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-base")
    id("convention.publish-gradle-plugin")
    `kotlin-dsl`
}

dependencies {
    implementation(projects.core)
    implementation(projects.reporterBitbucket)
    implementation(projects.reporterGithub)
    implementation(projects.reporterGitlab)
}

gradlePlugin {
    plugins {
        create("Kotlin Binary Compatibility Reporter") {
            id = "com.lounah.kt-binary-reporter"
            implementationClass = "com.lounah.ktbinaryreporter.plugin.KotlinBinaryReporterPlugin"
            displayName = "Kotlin Binary Compatibility Reporter Plugin"
            description = "Kotlin Binary Compatibility checker, which performs Git reports."
        }
    }
}
