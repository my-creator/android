package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Network.Get.CommunitySmallNewGetData

data class GetCommunitySmallNewPostResponse(
    val data: ArrayList<CommunitySmallNewGetData>,
    val message: String,
    val status: Int,
    val success: Boolean
)