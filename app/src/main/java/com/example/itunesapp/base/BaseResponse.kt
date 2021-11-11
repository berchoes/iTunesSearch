package com.example.itunesapp.common

/**
 * Created by Berk Ç. on 10.11.2021.
 */
sealed class BaseResponse<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : BaseResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : BaseResponse<T>(data, message)
    class Loading<T>(data: T? = null) : BaseResponse<T>(data)

}
