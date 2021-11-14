package com.example.itunesapp.ui.list

import androidx.lifecycle.viewModelScope
import com.example.itunesapp.base.BaseViewModel
import com.example.itunesapp.common.Constants
import com.example.itunesapp.common.SearchTypes
import com.example.itunesapp.domain.usecase.ContentSearchUseCase
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
    private val contentSearchUseCase: ContentSearchUseCase
) :
    BaseViewModel() {

    private var fullList = mutableListOf<SearchItem>()
    private var pagedList = mutableListOf<SearchItem>()

    var latestSearchQuery: String? = null

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage

    private val _resultInfo = MutableStateFlow<Triple<List<SearchItem>?, String,Boolean>?>(null)
    val resultInfo: StateFlow<Triple<List<SearchItem>?,String,Boolean>?> = _resultInfo

    //triggered by the inverse binding adapter(in ChipGroupBindingAdapter.kt) when chip selection changes.
    val searchType = MutableStateFlow(SearchTypes.ALL)

    private val _isEmptyResult = MutableStateFlow(false)
    val isEmptyResult : StateFlow<Boolean> = _isEmptyResult


    fun search(query: String = Constants.SEARCH_ALL, fromSearchBar: Boolean = false) = viewModelScope.launch {
        contentSearchUseCase(searchType.value.type, query)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _isEmptyResult.value = response.data.isNullOrEmpty()
                        fullList.clear()
                        fullList.addAll(response.data)
                        if (pagedList.isEmpty() || fromSearchBar) loadNewPage(fromSearchBar)
                    }
                    is Resource.Error -> {
                        _errorMessage.emit(response.error.errorMessage)
                    }
                }
            }
    }

    //The api doesn't have a parameter to get pages. data is manually paged here.
    fun loadNewPage(fromSearchBar: Boolean = false) = viewModelScope.launch {
        if(fromSearchBar) pagedList.clear()
        pagedList.addAll(fullList.take(25))
        _resultInfo.value = Triple(pagedList,generateUniqueListID(),fromSearchBar)
        fullList = fullList.drop(25).toMutableList()
    }



    //Creating a unique id for triggering result list StateFlow on every emit.
    private fun generateUniqueListID(): String = UUID.randomUUID().toString()
}