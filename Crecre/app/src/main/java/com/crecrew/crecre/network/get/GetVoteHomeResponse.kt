package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.VoteData

data class GetVoteHomeResponse (
    val status: Int,
    val success: Boolean,
    val data: VoteData
)