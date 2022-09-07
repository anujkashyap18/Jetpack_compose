package com.hillside.myapplication.retrofit.util

import androidx.lifecycle.LiveData
import com.hillside.myapplication.retrofit.Post

sealed class ApiState {

    class Success(val data: List<Post>) : ApiState()
    class Error(val msg: Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}
