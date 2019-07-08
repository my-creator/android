package com.crecrew.crecre.Network

import com.crecrew.crecre.Network.Get.GetCommunitySmallNewPostResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface NetworkService {

    //최신글 5개 보여주기
    @GET("posts/new")
    fun getCommunitySmallNewPosts(

    ): Call<GetCommunitySmallNewPostResponse>


}