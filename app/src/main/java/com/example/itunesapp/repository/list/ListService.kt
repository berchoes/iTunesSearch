package com.example.itunesapp.repository.list

import com.example.itunesapp.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Berk Ç. on 10.11.2021.
 */
interface ListService {

    @GET("search")
    suspend fun searchContent(@Query("entity") type: String? = null, @Query("term") query: String = "all") : ResponseModel
}