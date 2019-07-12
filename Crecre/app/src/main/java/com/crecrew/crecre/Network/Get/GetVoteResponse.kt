package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.VoteData

data class GetVoteResponse (
    val status: Int,
    val success: Boolean,
    val data: ArrayList<VoteData>
)