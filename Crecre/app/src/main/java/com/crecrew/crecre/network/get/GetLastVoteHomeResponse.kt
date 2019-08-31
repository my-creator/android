package com.crecrew.crecre.network.get

import com.crecrew.crecre.data.LastVoteHomeData

data class GetLastVoteHomeResponse (
    var status: Int,
    var success: Boolean,
    var data: ArrayList<LastVoteHomeData>
)