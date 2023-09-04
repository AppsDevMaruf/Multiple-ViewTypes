package com.example.multipleviewtype.repos
import com.example.multipleviewtype.api.PublicApi
import javax.inject.Inject

class PublicRepository @Inject constructor(private val publicApi: PublicApi) {

    suspend fun postsRepo() = publicApi.getPosts()


}