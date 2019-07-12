package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.VoteCurrentItemData

data class VoteTestData(
    var vote_idx : Int,
    var thumbnail_url : String,
    var create_time: String,
    var start_time: String,
    var end_time: String,
    var title : String,
    var contents : String,
    var type : String,
    var my_choice: Int,
    var choices : ArrayList<VoteCurrentItemData>
    )