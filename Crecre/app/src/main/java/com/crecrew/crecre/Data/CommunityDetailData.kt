package com.crecrew.crecre.Data

data class CommunityDetailData(
    val board_idx: Int,
    val contents: String,
    val create_time: String,
    val id: String,
    val post_idx: Int,
    val is_anonymous: Int,
    val nickname: String,
    val profile_url: String,
    val reply_cnt: Int,
    val title: String,
    val user_idx: Int,
    val view_cnt : Int,
    val media_type : String,
    val media_url : String,
    val board_name : String,
    val like_cnt : Int,
    val hate_cnt : Int,
    val is_like : Int,
    val is_hate : Int,
    val write_user_idx : Int,
    val login_userIdx : Int

)
