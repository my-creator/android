package com.crecrew.crecre.Data

data class CreatorData(
    val channel_id: String,
    val contents: String,
    val creator_create_time: String,
    val creator_idx: Int,
    val creator_name: String,
    val current_rank: Int,
    val follower_cnt: Int,
    val follower_grade_idx: Int,
    val last_rank: Int,
    val profile_url: String,
    val ranking: Int,
    val searchCnt: Int,
    val view_grade_idx: Int,
    val youtube_subscriber_cnt: Int,
    val youtube_view_cnt: Int
)