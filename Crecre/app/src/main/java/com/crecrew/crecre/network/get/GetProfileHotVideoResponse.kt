package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.HotVideoData

data class GetProfileHotVideoResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : ArrayList<HotVideoData>
)