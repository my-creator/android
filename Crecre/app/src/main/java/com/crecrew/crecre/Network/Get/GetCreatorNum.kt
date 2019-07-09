package com.crecrew.crecre.Network.Get
import com.crecrew.crecre.Data.CreatorNumData

data class GetCreatorNum(
    val data: ArrayList<CreatorNumData>,
    val message: String,
    val status: Int,
    val success: Boolean
)