package com.example.itunesapp.model

/**
 * Created by Berk Ç. on 10.11.2021.
 */

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val error: NetworkError) : Resource<T>()
}
