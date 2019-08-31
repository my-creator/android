package com.crecrew.crecre.network.get

data class GetCreatorSearch(
    val data: ArrayList<RankData>,
    val message: String,
    val status: Int,
    val success: Boolean
)