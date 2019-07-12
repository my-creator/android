package com.crecrew.crecre.Network.Get

import com.crecrew.crecre.Data.LastVoteHomeData

data class GetLastVoteHomeResponse (
    var status: Int,
    var success: Boolean,
    var data: ArrayList<LastVoteHomeData>
)