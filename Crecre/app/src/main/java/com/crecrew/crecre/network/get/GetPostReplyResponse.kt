package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.CommunityReplyData

data class GetPostReplyResponse(
    val data: ArrayList<CommunityReplyData>,
    val message: String,
    val status: Int,
    val success: Boolean
)