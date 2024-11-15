package org.map_bd.sotmasia2024.api


import org.map_bd.sotmasia2024.model.PostResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Gapi {

    @FormUrlEncoded
    @POST("exec")
    fun postData(
        @Field("model")model:String,
        @Field("brand")brand:String,
        @Field("ids")ids:String
    ): Call<PostResponse>
}