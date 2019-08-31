package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.GetVoteEndData

data class GetVoteEndResponse (
    val status: Int,
    val success: Boolean,
    val data : ArrayList<GetVoteEndData>
)