package com.example.itunesapp.repository.list

import com.example.itunesapp.domain.repository.ListRepository
import com.example.itunesapp.model.ResponseModel
import javax.inject.Inject

/**
 * Created by Berk Ç. on 11.11.2021.
 */
class ListRepositoryImpl @Inject constructor(private val service: ListService): ListRepository {

    override suspend fun searchContent(type: String? , query: String): ResponseModel = service.searchContent(type,query)

}