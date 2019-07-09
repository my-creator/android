package com.crecrew.crecre.Network.Get
import com.crecrew.crecre.Data.CreatorSearchData

data class GetCreatorSearch(
    val data: ArrayList<CreatorSearchData>,
    val message: String,
    val status: Int,
    val success: Boolean
)