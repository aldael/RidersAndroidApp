package com.example.protorider0.data

import android.content.Context
import com.example.protorider0.model.Rider
import kotlinx.coroutines.delay
import com.example.protorider0.data.local.AppDatabase
import com.example.protorider0.data.local.toLocal
import com.example.protorider0.data.local.toExternal
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataRiders {
    // private val BASE_URL = "http://localhost:8080/rider"
    private val BASE_URL = "https://run.mocky.io/v3/ff4ad82e-711e-4e64-bfb6-bf7a88f6b937"


    suspend fun getRiders(context: Context) : ArrayList<Rider> {
        var rLocal = AppDatabase.getInstance(context).ridersDao().getAll()
        if (rLocal.size > 0) {
             return rLocal.toExternal() as ArrayList<Rider>
        }

        val api = Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build().create(RidersAPI::class.java)

       var result = api.getRiders().execute()

       return if (result.isSuccessful) {
           var rList = result.body() ?: ArrayList<Rider>()
           if (rList.size > 0) {
                var rListLocal = rList.toLocal().toTypedArray()
                AppDatabase.getInstance(context).ridersDao().insert(*rListLocal)
           }

           rList
       } else {
           ArrayList<Rider>()
       }
    }
}

