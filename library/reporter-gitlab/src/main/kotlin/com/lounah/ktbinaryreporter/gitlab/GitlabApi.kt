package com.lounah.ktbinaryreporter.gitlab

import com.lounah.ktbinaryreporter.api.Credentials
import com.lounah.ktbinaryreporter.api.RetrofitProvider
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

public interface GitlabApi {

    @POST("projects/{id}/merge_requests/{mr_id}/notes")
    public suspend fun sendComment(
        @Query("id") projectId: String,
        @Query("mr_id") mrId: String,
        @Body comment: String
    )

    public companion object {

        public fun create(baseUrl: String, credentials: Credentials, logging: Boolean): GitlabApi {
            return RetrofitProvider(baseUrl, logging).provide(credentials).create()
        }
    }
}
