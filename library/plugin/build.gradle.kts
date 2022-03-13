@file:Suppress("UnstableApiUsage")

plugins {
    id("convention.kotlin-base")
    `kotlin-dsl`
}

dependencies {
    implementation(projects.library.core)
    implementation(projects.library.reporterBitbucket)
    implementation(projects.library.reporterGithub)
    implementation(projects.library.reporterGitlab)
}
