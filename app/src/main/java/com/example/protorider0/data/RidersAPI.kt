package com.example.protorider0.data

import com.example.protorider0.model.Rider
import retrofit2.Call
import retrofit2.http.GET

interface RidersAPI {
    @GET("getAll")
    fun getRiders() : Call<ArrayList<Rider>>
}