package com.example.multipleviewtype.di

import com.example.multipleviewtype.api.APIs.BASE_URL
import com.example.multipleviewtype.api.PublicApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
    @Provides
    @Singleton
    fun providesUserApi(retrofitBuilder: Retrofit.Builder): PublicApi {
        return retrofitBuilder.build().create(PublicApi::class.java)
    }

}