package com.coenrad.myuser.network

import com.coenrad.myuser.model.Users
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Webservices {
    @GET("users/{id}")
    suspend fun getUserByID(@Path("id") id: Int?): Users
}

val myApi: Webservices by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Webservices::class.java)
}



