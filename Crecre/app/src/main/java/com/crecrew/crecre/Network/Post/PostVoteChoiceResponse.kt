package com.crecrew.crecre.Network.Post

data class PostVoteChoiceResponse (
    var status: Int,
    var success: Boolean,
    var data: String //success to take vote 와 같은 말들 ㅇㅇ
)