package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.CommunityRequestCntData

data class GetBoardRequestNumResponse(
    val data: ArrayList<CommunityRequestCntData>,
    val message: String,
    val status: Int,
    val success: Boolean
)