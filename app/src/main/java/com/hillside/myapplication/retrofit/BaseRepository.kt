package com.hillside.myapplication.retrofit

import com.hillside.myapplication.retrofit.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BaseRepository

@Inject
constructor(private val apiService: ApiService){

    fun getPost():Flow<List<Post>> = flow{
        emit(apiService.getPost())
    }.flowOn(Dispatchers.IO)
}