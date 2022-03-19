plugins {
    `maven-publish`
}

group = "com.lounah.kt-binary-reporter"

version = providers.systemProperty("version")
    .forUseAtConfigurationTime()
    .get()

publishing.publications.withType<MavenPublication> {
    pom {
        name.set("Kotlin Binary Compatibility Reporter")
        description.set("Kotlin Binary compatibility checker, which performs Git reports.")
        url.set("https://github.com/Lounah/binary-compatibility-reproter")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://github.com/Lounah/binary-compatibility-reproter/blob/develop/LICENSE")
            }
        }
        developers {
            developer {
                id.set("lounah")
                name.set("Maksim Shchepalin")
                url.set("https://github.com/lounah")
            }
        }
    }
}
