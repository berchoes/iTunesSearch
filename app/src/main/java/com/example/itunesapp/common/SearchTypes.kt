package com.example.itunesapp.common

/**
 * Created by Berk Ç. on 11.11.2021.
 */

enum class SearchTypes(val type: String?) {
    ALL(null),
    MOVIES("movie"),
    EBOOK("ebook"),
    MUSIC("album"),
    PODCAST("podcast")
}
