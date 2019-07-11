package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.GetVoteEndData

data class GetVoteEndResponse (
    val status: Int,
    val success: Boolean,
    val data : ArrayList<GetVoteEndData>
)