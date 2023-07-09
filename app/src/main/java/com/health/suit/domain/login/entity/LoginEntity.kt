package com.health.suit.domain.login.entity

import com.google.gson.annotations.SerializedName

data class LoginEntity (
    @SerializedName("username") val last_name: String,
    @SerializedName("password") val phone: String
)