package com.example.itunesapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesapp.common.Resource
import com.example.itunesapp.domain.usecase.SearchAllUseCase
import com.example.itunesapp.model.SearchItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Berk Ç. on 11.11.2021.
 */

@HiltViewModel
class ListViewModel @Inject constructor(private val searchAllUseCase: SearchAllUseCase) :
    ViewModel() {

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage

    private val _resultList = MutableStateFlow<List<SearchItem>?>(null)
    val resultList: StateFlow<List<SearchItem>?> = _resultList


    fun searchAll(query: String = "all") = viewModelScope.launch {
        searchAllUseCase(query).collect { response ->
            when (response) {
                is Resource.Success -> {
                    _resultList.value = response.data
                }
                is Resource.Error -> {
                    _errorMessage.emit(response.error.message)
                }
            }
        }
    }
}