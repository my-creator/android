package com.crecrew.crecre.Network.Get

data class GetPostReplyResponse(
    val data: ArrayList<CommunityReplyData>,
    val message: String,
    val status: Int,
    val success: Boolean
)