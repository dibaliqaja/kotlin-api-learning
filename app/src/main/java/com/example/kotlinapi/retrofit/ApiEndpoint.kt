package com.example.kotlinapi.retrofit

import retrofit2.http.GET

interface ApiEndpoint {
    @GET("photos")
    fun getPhotos()
}