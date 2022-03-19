plugins {
    `kotlin-dsl`
}

group = "com.lounah.kt-binary-reporter.buildconventions"

dependencies {
    // workaround for https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}