package com.crecrew.crecre.data

data class NewVideoData(
    val channel_id: String,
    val create_time: String,
    val creator_idx: Int,
    val idx: Int,
    val thumbnail_url: String,
    val title: String,
    val video_url: String,
    val view_cnt: Int
)