package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.StatData

data class GetProfileStatResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : StatDataTest
)

data class StatDataTest (
    val avg_stat: Double,
    val join_cnt_stat: Int,
    val stat : ArrayList<StatDataTest2>
)

data class StatDataTest2 (
    val name: String,
    val stat_idx: Int,
    val stat_score: Float
)