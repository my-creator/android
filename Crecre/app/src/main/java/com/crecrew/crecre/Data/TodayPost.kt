package com.crecrew.crecre.Data

data class TodayPost(
    val board_idx: Int,
    val contents: String,
    val create_time: String,
    val creator_idx: Any,
    val hate_cnt: Int,
    val idx: Int,
    val image_cnt: Int,
    val is_anonymous: Int,
    val like_cnt: Int,
    val name: String,
    val post_idx: Int,
    val thumbnail_url: String,
    val title: String,
    val type: String,
    val update_time: String,
    val user_idx: Int,
    val video_cnt: Int,
    val view_cnt: Int,
    val reply_cnt: Int
)