package com.example.itunesapp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesapp.base.BaseViewModel
import com.example.itunesapp.common.Constants
import com.example.itunesapp.model.SearchItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Berk Ç. on 11.11.2021.
 */

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

     private val currentObjectJson: String?
        get() = savedStateHandle.get(Constants.SEND_ITEM_TO_DETAIL_PAGE)

    private val _currentData = MutableStateFlow<SearchItem?>(null)
    val currentData: StateFlow<SearchItem?> = _currentData

    init {
        currentObjectJson?.let {
            setDetailScreen(it)
        }
    }

    private fun setDetailScreen(json: String) = viewModelScope.launch {
        val type = object : TypeToken<SearchItem>() {}.type
        val obj = Gson().fromJson<SearchItem>(json, type)
        _currentData.emit(obj)
    }
}