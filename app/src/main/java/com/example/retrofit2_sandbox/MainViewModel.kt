package com.example.retrofit2_sandbox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit2_sandbox.data.MainViewState
import com.example.retrofit2_sandbox.data.MarsApiService
import com.example.retrofit2_sandbox.data.MarsProperty
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        marsApiService.getProperties().enqueue(
            object : Callback<List<MarsProperty>> {
                override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) =
                    _viewState.postValue(
                        MainViewState.Error("Failure: " + t.message)
                    )

                override fun onResponse(
                    call: Call<List<MarsProperty>>,
                    response: Response<List<MarsProperty>>
                ) = _viewState.postValue(
                    MainViewState.Success(response.body()!!)
                )
            }
        )
    }
}