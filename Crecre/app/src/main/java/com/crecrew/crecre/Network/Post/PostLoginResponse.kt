package com.crecrew.crecre.Network.Post

class PostLoginResponse (
    var status: Int,
    var success: Boolean,
    var message: String,
    var data: Token
)

class Token(
    var token: Token2
)

class Token2 (
    var token: String,
    var refreshToken:String
)