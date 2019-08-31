package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.VoteData

data class GetVoteResponse (
    val status: Int,
    val success: Boolean,
    val data: ArrayList<VoteData>
)