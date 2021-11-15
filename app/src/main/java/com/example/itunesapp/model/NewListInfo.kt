package com.example.itunesapp.model

/**
 * Created by Berk Ç. on 15.11.2021.
 */
data class NewListInfo(
    val list : List<SearchItem>,
    val id: String,
    val isSearchedList: Boolean
)
