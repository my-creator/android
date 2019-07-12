package com.crecrew.crecre.Network.Post

import com.crecrew.crecre.Data.SignupData

data class PostSignupResponse(
    val status: Int,
    val success: Boolean,
    val message: String
)