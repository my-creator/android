package com.crecrew.crecre.network.get

data class GetVoteCurrentResponse(
    val status: Int,
    val success: Boolean,
    val data: ArrayList<VoteTestData>
)