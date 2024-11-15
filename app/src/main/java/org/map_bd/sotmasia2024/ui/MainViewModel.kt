package org.map_bd.sotmasia2024.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.map_bd.sotmasia2024.api.RetrofitClint
import org.map_bd.sotmasia2024.model.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){
    val status =MutableLiveData<String>()
    fun postData(model: String, brand:String, ids:String){
        RetrofitClint.instance
            .postData(model,brand,ids)
            .enqueue(object : Callback<PostResponse>{
                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {
                    if (response.isSuccessful){
                        status.postValue(response.body()?.status)
                    }
                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    //Log.e("onFailure", t.message)
                }

            })
    }
    fun getStatus(): LiveData<String>{
        return status
    }
}