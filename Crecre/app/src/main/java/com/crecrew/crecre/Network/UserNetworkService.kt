package com.crecrew.crecre.Network

import com.crecrew.crecre.Network.Get.GetIdDuplicateResponse
import com.crecrew.crecre.Network.Get.GetRankResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface UserNetworkService {

    @GET("auth/duplicate/{id}")
    fun getIdDuplicate(
        @Path("id") id:String
    ): Call<GetIdDuplicateResponse>
}