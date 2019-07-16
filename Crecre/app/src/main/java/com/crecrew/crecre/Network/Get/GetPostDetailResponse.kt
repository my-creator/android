package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.CommunityDetailData

data class GetPostDetailResponse(
    val data: ArrayList<CommunityDetailData>,
    val message: String,
    val status: Int,
    val success: Boolean
)