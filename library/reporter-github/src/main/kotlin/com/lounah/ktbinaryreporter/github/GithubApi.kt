package com.lounah.ktbinaryreporter.github

import com.lounah.ktbinaryreporter.api.Credentials
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

        public fun create(baseUrl: String, credentials: Credentials, logging: Boolean): GithubApi {
            return RetrofitProvider(baseUrl, logging).provide(credentials).create()
        }
    }
}
