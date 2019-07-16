package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.CommunityRequestCntData

data class GetBoardRequestNumResponse(
    val data: ArrayList<CommunityRequestCntData>,
    val message: String,
    val status: Int,
    val success: Boolean
)