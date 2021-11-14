package com.example.itunesapp.domain.repository

import com.example.itunesapp.model.ResponseModel

/**
 * Created by Berk Ç. on 10.11.2021.
 */
interface ListRepository {

    suspend fun search(type: String?, query: String): ResponseModel
}