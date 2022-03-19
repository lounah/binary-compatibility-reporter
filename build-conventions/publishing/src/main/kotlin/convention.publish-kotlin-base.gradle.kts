import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.getByType

plugins.withId("kotlin") {
    extensions.getByType<JavaPluginExtension>().run {
        withSourcesJar()
        withJavadocJar()
    }
}
