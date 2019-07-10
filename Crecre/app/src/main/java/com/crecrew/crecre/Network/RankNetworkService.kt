package com.crecrew.crecre.Network

import com.crecrew.crecre.Network.Get.GetRankResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RankNetworkService {

    @GET("creators/all/subscribe/allrank")
    fun getAllAllSubscriber(
    ): Call<GetRankResponse>

    @GET("creator/all/subscribe/hotrank")
    fun getAllHotSubscriber(
    ): Call<GetRankResponse>

    @GET("creators/all/view/allrank")
    fun getAllAllView(
    ): Call<GetRankResponse>

    @GET("creators/all/view/hotrank")
    fun getAllHotView(
    ): Call<GetRankResponse>

    @GET("creators/{categoryIdx}/subscribe/allrank")
    fun getCateAllSubscriber(
        @Path("categoryIdx") categoryIdx: Int
    ) :Call<GetRankResponse>

    @GET("creators/{categoryIdx}/view/allrank")
    fun getCateAllView(
        @Path("categoryIdx") categoryIdx: Int
    ) :Call<GetRankResponse>
}