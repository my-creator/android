package com.crecrew.crecre.Network

import com.crecrew.crecre.Network.Get.GetVoteEndResponse
import com.crecrew.crecre.Network.Post.PostVoteSuggestData
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface VoteNetworkService {
    @POST("votes/suggest")
    fun suggestVote(
        @Header("Content-Type") content_type:String,
        @Body()body:JsonObject
    ): Call<PostVoteSuggestData>

    @GET("votes/lasts")
    fun getLastVote(
    ): Call<GetVoteEndResponse>

}