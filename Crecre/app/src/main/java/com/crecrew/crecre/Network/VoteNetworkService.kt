package com.crecrew.crecre.Network

import com.crecrew.crecre.Network.Get.GetVoteResponse
import com.crecrew.crecre.Data.PostVoteChoiceResponse
import com.crecrew.crecre.Network.Get.GetVoteEndResponse
import com.crecrew.crecre.Network.Get.GetVoteHomeResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface VoteNetworkService {
    /*@POST("votes/suggest")
    fun suggestVote(
        @Header("Content-Type") content_type:String,
        @Body()body:JsonObject
    ): Call<PostVoteSuggestData>*/

    @GET("votes/lasts")
    fun getLastVote(
    ): Call<GetVoteEndResponse>
    // TODO: GetVoteEndResponse랑 GetVoteResponse 합치기!

    @GET("votes/ings/newest")
    fun getCurrentVoteHome(
    ): Call<GetVoteHomeResponse>

    @GET("votes/ings")
    fun getCurrentVote(
    ):Call<GetVoteResponse>

    @POST("votes/:voteIdx/take")
    fun postVoteResponse(
        @Header("token") token: String,
        @Body() body: JsonObject
    ): Call<PostVoteChoiceResponse>

}