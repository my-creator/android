package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.VoteData

data class GetVoteHomeResponse (
    val status: Int,
    val success: Boolean,
    val data: VoteData
)