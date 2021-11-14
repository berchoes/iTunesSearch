package com.example.itunesapp.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Berk Ç. on 11.11.2021.
 */
abstract class BaseViewModel: ViewModel(), LifecycleObserver {

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    protected fun showLoading() = viewModelScope.launch {
        _loadingState.value = true
    }

    protected fun hideLoading() = viewModelScope.launch {
        _loadingState.value = false
    }

}