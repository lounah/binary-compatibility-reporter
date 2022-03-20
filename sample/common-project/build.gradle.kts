import com.lounah.ktbinaryreporter.api.Credentials

plugins {
    id("convention.kotlin-jvm")
    id("convention.publish-kotlin-library")
    id("com.lounah.kt-binary-reporter")
}

ktBinaryReporter {
    abiVersion = "0.1.1"

    ignoredClasses = setOf("")
    ignoredPackages = setOf("")

    reporter {
        credentials = Credentials.Basic(
            stringProperty("username"),
            stringProperty("password")
        )
//        github {
//            baseUrl = "https://api.github.com/"
//            owner = "Lounah"
//            repo = "binary-compatibility-reporter"
//            pullId = stringProperty("pullId")
//        }
    }
}

fun Project.stringProperty(name: String): String {
    return findProperty(name)?.toString().orEmpty()
}
