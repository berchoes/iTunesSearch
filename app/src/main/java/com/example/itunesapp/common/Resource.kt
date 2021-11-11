package com.example.itunesapp.common

import com.example.itunesapp.model.NetworkError

/**
 * Created by Berk Ç. on 10.11.2021.
 */

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val error: NetworkError) : Resource<T>()
}
