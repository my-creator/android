package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.CommunityBoardData

data class GetCommunityUnlikeBoardsResponse(
    val data: ArrayList<CommunityBoardData>,
    val message: String,
    val status: Int,
    val success: Boolean
)