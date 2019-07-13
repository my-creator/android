package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.StatData

data class GetProfileStatResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : ArrayList<StatData>
)