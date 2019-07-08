package com.crecrew.crecre.Network.Get

data class CommunitySmallNewGetData(
    val board_idx: Int,
    val contents: String,
    val create_time: String,
    val hate_cnt: Int,
    val idx: Int,
    val image_cnt: Int,
    val is_anonymous: Int,
    val like_cnt: Int,
    val thumbnail_url: Any,
    val title: String,
    val update_time: String,
    val user_idx: Int,
    val video_cnt: Int,
    val view_cnt: Int
)