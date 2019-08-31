package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.CreatorProfileData

data class GetProfileResponse(
    val data : CreatorProfileData,
    val status : Int,
    val success : Boolean,
    val message : String
)