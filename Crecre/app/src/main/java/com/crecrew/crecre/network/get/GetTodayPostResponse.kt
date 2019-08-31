package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.TodayPost

data class GetTodayPostResponse (
    var status: Int,
    var success: Boolean,
    var message: String,
    var data: ArrayList<TodayPost>
)