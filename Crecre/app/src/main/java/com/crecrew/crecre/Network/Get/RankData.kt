package com.crecrew.crecre.Network.Get

data class RankData(
    val categoryName: String,
    val creatorName: String,
    val img_url: String,
    val profile_url: String,
    val ranking: Int,
    val youtube_subscriber_cnt: Int,
    val youtube_view_cnt : Int,
    val upDown: Int,
    val idx : Int
)
