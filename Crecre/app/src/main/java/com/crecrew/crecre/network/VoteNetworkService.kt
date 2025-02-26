package com.crecrew.crecre.network

import com.crecrew.crecre.network.get.GetVoteResponse
import com.crecrew.crecre.network.post.PostVoteChoiceResponse
import com.crecrew.crecre.network.get.GetLastVoteHomeResponse
import com.crecrew.crecre.network.get.GetVoteEndResponse
import com.crecrew.crecre.network.get.GetVoteHomeResponse
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
        @Header("token") token: String?
    ): Call<GetVoteEndResponse>
    // TODO: GetVoteEndResponse랑 GetVoteResponse 합치기!

    @GET("votes/lasts/home")
    fun getLastVoteHome(
        @Header("token") token: String?
    ):Call<GetLastVoteHomeResponse>

    @GET("votes/ings/newest")
    fun getCurrentVoteHome(
        @Header("token") token: String?
    ): Call<GetVoteHomeResponse>

    @GET("votes/ings")
    fun getCurrentVote(
        @Header("token") token: String?
    ):Call<GetVoteResponse>

    @POST("votes/{voteIdx}/take")
    fun postVoteChoiceResponse(
        @Header("token") token: String,
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject,
        @Path("voteIdx") voteIdx: Int
    ): Call<PostVoteChoiceResponse>

}