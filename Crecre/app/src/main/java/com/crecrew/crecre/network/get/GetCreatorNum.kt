package com.crecrew.crecre.network.get
import com.crecrew.crecre.data.CreatorNumData

data class GetCreatorNum(
    val data: ArrayList<CreatorNumData>,
    val message: String,
    val status: Int,
    val success: Boolean
)