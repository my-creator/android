package com.crecrew.crecre.network.get

data class GetRankResponse(
    val data: ArrayList<RankData>,
    val status: Int,
    val success: Boolean
)