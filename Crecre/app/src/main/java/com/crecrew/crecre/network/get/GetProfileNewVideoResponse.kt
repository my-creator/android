package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.NewVideoData

data class GetProfileNewVideoResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : ArrayList<NewVideoData>
)