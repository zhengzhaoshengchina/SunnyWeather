package com.example.sunnyweather.logic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

object Repository {

    fun searchPlaces(query:String) = liveData(Dispatchers.IO) {
        Log.d("qaq","进入了查询方法")
        val result = try{
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            Log.d("qaq",placeResponse.status)
            if(placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }



}