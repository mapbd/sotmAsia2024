package org.map_bd.sotmasia2024.api

import org.map_bd.sotmasia2024.UsersItem
import retrofit2.Call
import retrofit2.http.GET

interface Day1Room1 {
    @GET("day1room1.json")
    fun getData() : Call<List<UsersItem>>
}