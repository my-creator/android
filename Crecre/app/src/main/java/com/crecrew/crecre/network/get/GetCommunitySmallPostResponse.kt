package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.CommunitySmallGetData

data class GetCommunitySmallPostResponse(
    val data: ArrayList<CommunitySmallGetData>,
    val message: String,
    val status: Int,
    val success: Boolean
)