package com.lounah.ktbinaryreporter.github

import com.lounah.ktbinaryreporter.api.RetrofitProvider
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

public interface GithubApi {

    @POST("repos/{owner}/{repo}/pulls/{id}/comments")
    public suspend fun sendComment(
        @Query("owner") owner: String,
        @Query("repo") repo: String,
        @Query("id") pullId: String,
        @Body comment: String
    )

    public companion object {

        private const val BASE_URL: String = ""

        public fun create(
            username: String,
            password: String,
            baseUrl: String = BASE_URL,
            logging: Boolean = false
        ): GithubApi {
            return RetrofitProvider(baseUrl, logging).provide(username, password).create()
        }

        public fun create(
            credentials: String,
            baseUrl: String = BASE_URL,
            logging: Boolean = false
        ): GithubApi {
            return RetrofitProvider(baseUrl, logging).provide(credentials).create()
        }

        public fun create(
            tokenProvider: () -> String,
            baseUrl: String = BASE_URL,
            logging: Boolean = false
        ): GithubApi {
            return RetrofitProvider(baseUrl, logging).provide(tokenProvider).create()
        }
    }
}
