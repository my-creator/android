package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.CreatorData

data class GetCreatorTodayHotRank(
    val data: ArrayList<CreatorData>,
    val message: String,
    val status: Int,
    val success: Boolean
)