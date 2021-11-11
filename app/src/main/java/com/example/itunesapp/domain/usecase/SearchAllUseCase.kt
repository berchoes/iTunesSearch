package com.example.itunesapp.domain.usecase

import com.example.itunesapp.common.BaseResponse
import com.example.itunesapp.domain.repository.ListRepository
import com.example.itunesapp.model.SearchItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Berk Ç. on 11.11.2021.
 */
class SearchAllUseCase @Inject constructor(private val repository: ListRepository) {

    operator fun invoke(): Flow<BaseResponse<List<SearchItem>>> = flow {

        try {
            emit(BaseResponse.Loading())
            val result = repository.getAllResults().results
            emit(BaseResponse.Success(result))

        } catch (e: HttpException) {
            emit(BaseResponse.Error<List<SearchItem>>(e.localizedMessage ?: "Error"))

        } catch (e: IOException) {
            emit(BaseResponse.Error<List<SearchItem>>(e.localizedMessage ?: "No Internet"))
        }
    }
}