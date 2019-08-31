package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.CommunityDetailData

data class GetPostDetailResponse(
    val data: ArrayList<CommunityDetailData>,
    val message: String,
    val status: Int,
    val success: Boolean
)