package com.crecrew.crecre.Network

import com.crecrew.crecre.Network.Get.GetIdDuplicateResponse
import com.crecrew.crecre.Network.Get.GetRankResponse
import com.crecrew.crecre.Network.Post.PostLoginResponse
import com.crecrew.crecre.Network.Post.PostSignupResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserNetworkService {

    @GET("auth/duplicate/{id}")
    fun getIdDuplicate(
        @Path("id") id: String
    ): Call<GetIdDuplicateResponse>

    @POST("auth/signup")
    fun postSignup(
        @Body() body: JsonObject
    ): Call<PostSignupResponse>

    @POST("auth/signin")
    fun postLogin(
        @Body() body: JsonObject
    ): Call<PostLoginResponse>
}