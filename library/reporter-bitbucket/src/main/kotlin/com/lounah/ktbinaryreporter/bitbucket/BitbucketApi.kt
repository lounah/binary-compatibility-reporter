package com.lounah.ktbinaryreporter.bitbucket

import com.lounah.ktbinaryreporter.api.Credentials
import com.lounah.ktbinaryreporter.api.RetrofitProvider
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

public interface BitbucketApi {

    @POST("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/comments")
    public suspend fun sendComment(
        @Path("projectKey") projectKey: String,
        @Path("repositorySlug") repositorySlug: String,
        @Path("pullRequestId") pullRequestId: String,
        @Body comment: Comment
    )

    public companion object {

        public fun create(baseUrl: String, credentials: Credentials, logging: Boolean): BitbucketApi {
            return RetrofitProvider(baseUrl, logging).provide(credentials).create()
        }
    }
}

public data class Comment(val text: String)