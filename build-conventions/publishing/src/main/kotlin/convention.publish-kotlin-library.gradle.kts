plugins {
    id("convention.publish-base")
    id("convention.publish-kotlin-base")
}

publishing {
    publications {
        register<MavenPublication>("kotlinLibraryMaven") {
            from(components["java"])

            afterEvaluate {
                artifactId = project.name
            }
        }
    }
}
