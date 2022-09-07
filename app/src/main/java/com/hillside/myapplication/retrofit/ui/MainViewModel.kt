package com.hillside.myapplication.retrofit.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hillside.myapplication.retrofit.BaseRepository
import com.hillside.myapplication.retrofit.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: BaseRepository) : ViewModel() {

    val response:MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    init {
        getPost()
    }

    fun getPost() = viewModelScope.launch {
        repository.getPost()
            .onStart {
                response.value = ApiState.Loading
            }.catch {
                response.value = ApiState.Error(it)

            }.collect{
                response.value = ApiState.Success(it)
            }
    }
}