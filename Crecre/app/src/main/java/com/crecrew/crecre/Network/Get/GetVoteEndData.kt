package com.crecrew.crecre.Data

data class GetVoteEndData (
    val vote_idx: Int,
    var thumbnail_url: String,
    var create_time: String,
    var start_time: String,
    var end_time: String,
    var title: String,
    var contents: String,
    var type:String,
    var my_choice:Int?,
    var is_permitted :Int, // 이거 머임 ㅇㅅㅇ
    var choices :ArrayList<VoteChoiceData>
)