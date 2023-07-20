package com.example.protorider0.ui


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.protorider0.data.RidersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import com.example.protorider0.model.Rider
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {

    private val _TAG = "API-DEMO"
    private val coroutineContext: CoroutineContext = newSingleThreadContext("Riders")
    private val scope = CoroutineScope(coroutineContext)

    private val ridersRepo = RidersRepository()

    var riders = MutableLiveData<ArrayList<Rider>>()

    fun onStart(context: Context) {
        scope.launch {
            kotlin.runCatching {
                ridersRepo.getRiders(context)
            }.onSuccess {
                Log.d(_TAG, "Riders onSuccess")
                riders.postValue(it)
            }.onFailure {
                Log.e(_TAG, "Riders Error: " + it)
            }
        }
    }
}