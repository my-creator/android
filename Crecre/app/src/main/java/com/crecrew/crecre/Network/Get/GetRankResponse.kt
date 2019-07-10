package com.crecrew.crecre.Network.Get

data class GetRankResponse(
    val data: ArrayList<RankData>,
    val status: Int,
    val success: Boolean
)