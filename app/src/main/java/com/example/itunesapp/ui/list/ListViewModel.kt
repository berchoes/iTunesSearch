package com.example.itunesapp.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesapp.common.BaseResponse
import com.example.itunesapp.domain.usecase.SearchAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Berk Ç. on 11.11.2021.
 */

@HiltViewModel
class ListViewModel @Inject constructor(private val searchAllUseCase: SearchAllUseCase) :
    ViewModel() {

     fun searchAll() = viewModelScope.launch {
        searchAllUseCase().collect {
            when (it) {
                is BaseResponse.Success -> {
                    Log.e("asda", it.data?.get(0)?.artistName.toString())
                }
                is BaseResponse.Error -> {

                }
                is BaseResponse.Loading -> {

                }
            }
        }
    }
}