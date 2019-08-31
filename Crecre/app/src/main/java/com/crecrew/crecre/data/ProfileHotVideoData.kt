package com.crecrew.crecre.data

data class ProfileHotVideoData(
    var creator_idx : Int,
    var title : String,
    var video_url : String,
    var view_cnt : Int,
    var thumbnail_url : String,
    var create_time : String,
    var channel_id : String
)