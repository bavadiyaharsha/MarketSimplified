package com.example.marketsimplified

import com.example.marketsimplified.model.LocalRepoResp
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("repositories")
    fun repositories(): Call<LocalRepoResp>
}