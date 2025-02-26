package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.CreatorData

data class GetCreatorTodayHotRank(
    val data: ArrayList<CreatorData>,
    val message: String,
    val status: Int,
    val success: Boolean
)