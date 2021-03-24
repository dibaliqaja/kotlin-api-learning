package com.example.kotlinapi.retrofit

import com.example.kotlinapi.MainModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("photos")
    fun getPhotos(): Call<List<MainModel>>
}