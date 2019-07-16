package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.CommunityReplyData

data class GetPostReplyResponse(
    val data: ArrayList<CommunityReplyData>,
    val message: String,
    val status: Int,
    val success: Boolean
)