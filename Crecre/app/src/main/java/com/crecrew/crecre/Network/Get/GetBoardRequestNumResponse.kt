package com.crecrew.crecre.Network.Get

data class GetBoardRequestNumResponse(
    val data: ArrayList<CommunityRequestCntData>,
    val message: String,
    val status: Int,
    val success: Boolean
)