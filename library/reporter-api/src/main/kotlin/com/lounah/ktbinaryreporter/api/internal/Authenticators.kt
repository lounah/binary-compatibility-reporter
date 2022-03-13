package com.lounah.ktbinaryreporter.api.internal

import okhttp3.*

internal object Authenticators {

    fun withHeader(name: String, value: String) = Authenticator { _, response ->
        response.request
            .newBuilder()
            .addHeader(name, value)
            .build()
    }

    fun basic(username: String, password: String): Authenticator {
        return withHeader("Authorization", Credentials.basic(username, password))
    }

    fun bearer(token: String): Authenticator {
        return withHeader("Authorization", "Bearer $token")
    }

    fun base64(credentials: String): Authenticator {
        return withHeader("Authorization", "Basic $credentials")
    }
}