package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.TodayPost

data class GetTodayPostResponse (
    var status: Int,
    var success: Boolean,
    var message: String,
    var data: ArrayList<TodayPost>
)