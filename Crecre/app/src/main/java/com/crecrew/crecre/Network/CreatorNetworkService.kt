package com.crecrew.crecre.Network

import com.crecrew.crecre.Network.Get.GetCreatorNum
import com.crecrew.crecre.Network.Get.GetCreatorSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CreatorNetworkService {

    @GET("creators/allCreatorCnt")
    fun getCreatorNum(
        ): Call<GetCreatorNum>

    @GET("creators/creatorSearch")
    fun getCreatorSearch(
        @Query("name") name: String
        ): Call<GetCreatorSearch>

}

