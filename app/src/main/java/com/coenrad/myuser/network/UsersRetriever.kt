package com.coenrad.myuser.network

import com.coenrad.myuser.model.Users
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersRetriever {
    private var webservices: Webservices = myApi

    companion object{
        var baseURL ="https://jsonplaceholder.typicode.com/"
    }

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        webservices = retrofit.create(Webservices::class.java)
    }

    suspend fun getUsers(id: Int): Users{
        return webservices.getUserByID(id)
    }
}