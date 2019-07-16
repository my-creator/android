package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.CommunitySmallGetData

data class GetCommunitySmallPostResponse(
    val data: ArrayList<CommunitySmallGetData>,
    val message: String,
    val status: Int,
    val success: Boolean
)