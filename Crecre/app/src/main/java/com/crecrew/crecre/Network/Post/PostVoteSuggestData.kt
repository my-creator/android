package com.crecrew.crecre.Network.Post

data class PostVoteSuggestData (
    val access_token: String,
    val voteTiTle: String,
    val itemArray: Array<String>
)