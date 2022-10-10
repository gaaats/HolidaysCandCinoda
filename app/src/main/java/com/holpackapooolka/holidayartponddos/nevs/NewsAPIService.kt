package com.holpackapooolka.holidayartponddos.nevs

import com.holpackapooolka.holidayartponddos.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {


    companion object {
        const val COUNT_OF_PAGES_FOR_LOADING = 1
        val API_KEY_NEVS = BuildConfig.API_KEY_NEEEVS
        val NEVS_API_BASE_URL="https://newsapi.org/"
    }

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String = API_KEY_NEVS,
        @Query("page") page: Int = 1,
        @Query("q") queryAbout: String = "pl"
    ): Response<NevsApiResponse>
}