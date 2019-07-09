package com.crecrew.crecre.Network.Get

data class GetCommunityUnlikeBoardsResponse(
    val data: ArrayList<CommunityBoardData>,
    val message: String,
    val status: Int,
    val success: Boolean
)