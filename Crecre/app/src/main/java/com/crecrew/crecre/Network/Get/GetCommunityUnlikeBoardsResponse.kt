package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.CommunityBoardData

data class GetCommunityUnlikeBoardsResponse(
    val data: ArrayList<CommunityBoardData>,
    val message: String,
    val status: Int,
    val success: Boolean
)