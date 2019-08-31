package com.crecrew.crecre.data

data class VoteCurrentItemData(
    var choice_idx: String,
    var name: String,
    var count: Int,
    var creator_profile_url: String,
    var follower_grade_idx: String,
    var follower_grade_name: String,
    var follower_grade_level: String,
    var follower_grade_img_url: String,
    var view_grade_img_url: String,
    var rank: Int
)