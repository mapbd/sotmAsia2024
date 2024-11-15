package org.map_bd.sotmasia2024.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitMapClint {

    private const val BASE_URL = "https://script.google.com/macros/s/AKfycbxTHSFqLEfrBXaQz20kaK3wdEBJ6wKlGgQ083016hc-DMGqS46ASLj7Qd0hySYfKlvH/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instance = retrofit.create(Gmapi::class.java)
}