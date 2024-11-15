package org.map_bd.sotmasia2024.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClint {
    private const val BASE_URL = "https://script.google.com/macros/s/AKfycbwYD5-p4KmqJWz3mKkhArxnvYCDkXgIoJ3QcTNXihqCF50WgkdG0BCoeo5Lh7mU137DEA/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instance = retrofit.create(Gapi::class.java)
}