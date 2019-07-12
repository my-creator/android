package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.HotVideoData

data class GetProfileHotVideoResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : ArrayList<HotVideoData>
)