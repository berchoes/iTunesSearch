package com.example.itunesapp.util.extensions

import androidx.appcompat.widget.SearchView

/**
 * Created by Berk Ç. on 14.11.2021.
 */

fun SearchView.setQueryListener(onSubmit: (String?) -> Unit, onQueryChanged: (String?) -> Unit){
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            onSubmit.invoke(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
           onQueryChanged.invoke(newText)
            return true
        }
    })
}