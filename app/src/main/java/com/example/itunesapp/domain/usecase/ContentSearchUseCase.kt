package com.example.itunesapp.domain.usecase

import com.example.itunesapp.model.Resource
import com.example.itunesapp.domain.repository.ListRepository
import com.example.itunesapp.model.NetworkError
import com.example.itunesapp.model.SearchItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Berk Ç. on 11.11.2021.
 */
class ContentSearchUseCase @Inject constructor(private val repository: ListRepository) {

    operator fun invoke(type: String?,query: String): Flow<Resource<List<SearchItem>>> = flow {

        try {
            val result = repository.search(type, query).results
            emit(Resource.Success(result))

        } catch (e: Exception) {
            emit(
                Resource.Error<List<SearchItem>>(
                    NetworkError(
                        e.localizedMessage ?: "An unexpected error occurred."
                    )
                )
            )
        }
    }
}