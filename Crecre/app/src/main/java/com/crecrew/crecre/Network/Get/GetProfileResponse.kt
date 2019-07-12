package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.CreatorProfileData

data class GetProfileResponse(
    val data : CreatorProfileData,
    val status : Int,
    val success : Boolean,
    val message : String
)