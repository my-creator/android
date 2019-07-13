package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.HotVideoData
import com.crecrew.crecre.Data.NewVideoData

data class GetProfileNewVideoResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : ArrayList<NewVideoData>
)