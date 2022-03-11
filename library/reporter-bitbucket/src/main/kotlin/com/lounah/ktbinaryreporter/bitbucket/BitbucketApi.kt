package com.lounah.ktbinaryreporter.bitbucket

import com.lounah.ktbinaryreporter.api.RetrofitProvider
import retrofit2.Call
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

public interface BitbucketApi {

    @POST("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments")
    public suspend fun sendComment(
        @Path("projectKey") projectKey: String,
        @Path("repositorySlug") repositorySlug: String,
        @Path("pullRequestId") pullRequestId: String,
        @Body comment: Comment
    )

    public companion object {

        private const val BASE_URL: String = ""

        public fun create(
            username: String,
            password: String,
            baseUrl: String = BASE_URL,
            logging: Boolean = false
        ): BitbucketApi {
            return RetrofitProvider(baseUrl, logging).provide(username, password).create()
        }

        public fun create(
            credentials: String,
            baseUrl: String = BASE_URL,
            logging: Boolean = false
        ): BitbucketApi {
            return RetrofitProvider(baseUrl, logging).provide(credentials).create()
        }

        public fun create(
            tokenProvider: () -> String,
            baseUrl: String = BASE_URL,
            logging: Boolean = false
        ): BitbucketApi {
            return RetrofitProvider(baseUrl, logging).provide(tokenProvider).create()
        }
    }
}

public data class Comment(val text: String)