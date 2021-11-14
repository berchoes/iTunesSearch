package com.example.itunesapp.ui.list

import androidx.lifecycle.viewModelScope
import com.example.itunesapp.base.BaseViewModel
import com.example.itunesapp.common.SearchTypes
import com.example.itunesapp.domain.usecase.SearchAllUseCase
import com.example.itunesapp.model.Resource
import com.example.itunesapp.model.SearchItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * Created by Berk Ç. on 11.11.2021.
 */

@HiltViewModel
class ListViewModel @Inject constructor(
    private val searchAllUseCase: SearchAllUseCase
) :
    BaseViewModel() {

    private var fullList = mutableListOf<SearchItem>()
    private var pagedList = mutableListOf<SearchItem>()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage

    private val _resultList = MutableStateFlow<Pair<List<SearchItem>?,Int>?>(null)
    val resultList: StateFlow<Pair<List<SearchItem>?,Int>?> = _resultList

    private val _searchType = MutableStateFlow(SearchTypes.ALL)
    val searchType: StateFlow<SearchTypes> = _searchType


    fun searchAll(query: String = SearchTypes.ALL.type) = viewModelScope.launch {
        searchAllUseCase(query)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .collect { response ->
                when (response) {
                    is Resource.Success -> {
                        fullList.clear()
                        fullList.addAll(response.data)
                        if (pagedList.isEmpty()) loadNewPage()
                    }
                    is Resource.Error -> {
                        _errorMessage.emit(response.error.errorMessage)
                    }
                }
            }
    }

    fun loadNewPage() = viewModelScope.launch {
        pagedList.addAll(fullList.take(25))
        _resultList.value = Pair(pagedList,pagedList.size)
        fullList = fullList.drop(25).toMutableList()
    }
}