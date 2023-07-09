package com.health.suit.domain.login.entity

import com.google.gson.annotations.SerializedName

data class LoginApiResponse(
    @SerializedName("token") var msg : String
)
