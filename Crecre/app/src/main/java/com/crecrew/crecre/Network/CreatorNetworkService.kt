package com.crecrew.crecre.Network

import android.util.Log
import com.crecrew.crecre.Network.Get.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CreatorNetworkService {

    @GET("creators/allCreatorCnt")
    fun getCreatorNum(
        ): Call<GetCreatorNum>

    @GET("creators/creatorSearch")
    fun getCreatorSearch(
        @Query("name") name: String
        ): Call<GetCreatorSearch>

    @GET("creators/chart/hot")
    fun getCreatorTodayHotRank(
    ): Call<GetCreatorTodayHotRank>

    @GET("creators/profiles/{creatorIdx}")
    fun getProfileResponse(
        @Path("creatorIdx") creatorIdx : Int
    ): Call<GetProfileResponse>

    @GET("creators/profiles/stat/{creatorIdx}")
    fun getProfileStatResponse(
        @Path("creatorIdx") creatorIdx: Int
    ): Call<GetProfileStatResponse>

    @GET("creators/{creatorIdx}/popularvideo/three")
    fun getProfileHotVideoResponse(
        @Path("creatorIdx") creatorIdx: Int
    ): Call<GetProfileHotVideoResponse>

    @GET("creators/{creatorIdx}/newvideo/three")
    fun getProfileNewVideoResponse(
        @Path("creatorIdx") creatorIdx: Int
    ): Call<GetProfileNewVideoResponse>

    @GET("creators/profiles/stat/detail/{creatorIdx}")
    fun getCreatorStatResponse(
        @Path("creatorIdx") creatorIdx" Int
    ): Call<GetCreatorStatResponse>
}

