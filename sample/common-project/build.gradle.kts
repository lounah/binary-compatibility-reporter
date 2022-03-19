import com.lounah.ktbinaryreporter.api.Credentials

plugins {
    id("convention.kotlin-jvm")
    id("com.lounah.kt-binary-reporter")
}

ktBinaryReporter {
    abiVersion = "1.0.0"

    ignoredClasses = setOf("")
    ignoredPackages = setOf("")

    reporter {
        credentials = Credentials.Basic(
            stringProperty("username"),
            stringProperty("password")
        )
        github {
            baseUrl = "https://api.github.com/"
            owner = "Lounah"
            repo = "binary-compatibility-reporter"
            pullId = stringProperty("pullId")
        }
    }
}

dependencies {
    implementation("com.lounah.kt-binary-reporter:plugin")
}

fun Project.stringProperty(name: String): String {
    return findProperty(name)?.toString().orEmpty()
}
