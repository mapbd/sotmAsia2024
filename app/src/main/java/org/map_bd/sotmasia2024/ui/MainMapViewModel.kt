package org.map_bd.sotmasia2024.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.map_bd.sotmasia2024.api.RetrofitMapClint
import org.map_bd.sotmasia2024.model.PostResponsed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainMapViewModel: ViewModel(){
    val status = MutableLiveData<String>()
    fun postData(model: String, brand:String, ids:String,lat:String,long:String){
        RetrofitMapClint.instance
            .postData(model,brand,ids,lat,long)
            .enqueue(object : Callback<PostResponsed> {
                override fun onResponse(
                    call: Call<PostResponsed>,
                    response: Response<PostResponsed>
                ) {
                    if (response.isSuccessful){
                        status.postValue(response.body()?.status)
                    }
                }

                override fun onFailure(call: Call<PostResponsed>, t: Throwable) {
                    //Log.e("onFailure", t.message)
                }

            })
    }
    fun getStatus(): LiveData<String> {
        return status
    }
}