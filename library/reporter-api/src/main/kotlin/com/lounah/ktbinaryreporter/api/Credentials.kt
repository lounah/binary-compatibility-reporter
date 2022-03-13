package com.lounah.ktbinaryreporter.api

public sealed class Credentials {

    public class Basic(public val username: String, public val password: String) : Credentials()

    public class Bearer(public val tokenProvider: () -> String) : Credentials()

    public class Base64(public val credentials: String) : Credentials()

    public object None : Credentials()
}
