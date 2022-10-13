package com.example.retrofit2_sandbox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit2_sandbox.data.MainViewState
import com.example.retrofit2_sandbox.data.MarsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainViewModel : ViewModel() {
    private var _viewState = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState> = _viewState

    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"
    fun fetchData() {

        val moshi = Moshi.Builder().apply {
            add(KotlinJsonAdapterFactory())
        }.build()
        val retrofit = Retrofit.Builder().apply {
            addConverterFactory(MoshiConverterFactory.create(moshi))
            addConverterFactory(ScalarsConverterFactory.create())
            baseUrl(BASE_URL)
        }.build()
        val marsApiService = retrofit.create(MarsApiService::class.java)

        viewModelScope.launch {
            try {
                val result = marsApiService.getProperties()
                _viewState.postValue(
                    MainViewState.Success(result)
                )
            } catch (exception: Exception) {
                _viewState.postValue(
                    MainViewState.Error("Failure: " + exception.message)
                )
            }
        }
    }
}