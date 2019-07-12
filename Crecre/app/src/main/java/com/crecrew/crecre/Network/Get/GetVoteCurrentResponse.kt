package com.crecrew.crecre.Network.Get

data class GetVoteCurrentResponse(
    val status: Int,
    val success: Boolean,
    val data: ArrayList<VoteTestData>
)