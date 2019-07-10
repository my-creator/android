package com.crecrew.crecre.Network.Get

data class GetPostDetailResponse(
    val data: ArrayList<CommunityDetailData>,
    val message: String,
    val status: Int,
    val success: Boolean
)