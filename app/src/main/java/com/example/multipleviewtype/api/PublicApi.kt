package com.example.multipleviewtype.api

import com.example.multipleviewtype.api.APIs.POSTS
import com.example.multipleviewtype.data.network.ResponsePostData
import retrofit2.Response
import retrofit2.http.GET

interface PublicApi {
    @GET(POSTS)
    suspend fun getPosts(): Response<List<ResponsePostData.ResponsePostDataItem>>


}