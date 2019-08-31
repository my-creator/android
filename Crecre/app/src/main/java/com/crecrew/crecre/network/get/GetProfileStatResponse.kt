package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.StatData

data class GetProfileStatResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : ArrayList<StatData>
)