package com.example.itunesapp.model

/**
 * Created by Berk Ç. on 10.11.2021.
 */

data class ResponseModel(
    val resultCount: Int?,
    val results: List<SearchItem>
)
