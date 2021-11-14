package com.example.itunesapp.repository.list

import com.example.itunesapp.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Berk Ç. on 10.11.2021.
 */
interface ListService {

    @GET("search?limit=200")
    suspend fun searchContent(@Query("entity") type: String? , @Query("term") query: String) : ResponseModel
}