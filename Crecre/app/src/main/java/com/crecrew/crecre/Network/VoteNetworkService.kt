package com.crecrew.crecre.Network

import com.crecrew.crecre.Data.PostVoteChoiceResponse
import com.crecrew.crecre.Network.Get.GetVoteCurrentResponse
import com.crecrew.crecre.Network.Get.GetVoteEndResponse
import com.crecrew.crecre.Network.Post.PostVoteSuggestData
import com.google.gson.JsonObject
import org.json.JSONObject
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

    @GET("votes/ings")
    fun getCurrentVote(
    ): Call<GetVoteCurrentResponse>

    @POST("votes/:voteIdx/take")
    fun postVoteResponse(
        @Header("token") token: String,
        @Body() body: JsonObject
    ): Call<PostVoteChoiceResponse>

}