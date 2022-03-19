plugins {
    id("convention.publish-kotlin-base")
    id("convention.publish-base")
    id("java-gradle-plugin")
}

gradlePlugin {
    isAutomatedPublishing = false
}

publishing.publications {
    afterEvaluate {
        extensions.getByType<GradlePluginDevelopmentExtension>().plugins.all {
            register<MavenPublication>("${name}PluginMaven") {
                from(components["java"])
                artifactId = project.name
            }
        }
    }
}
