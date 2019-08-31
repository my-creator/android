package com.crecrew.crecre.network.post

data class PostVoteSuggestData (
    val access_token: String,
    val voteTiTle: String,
    val itemArray: Array<String>
)