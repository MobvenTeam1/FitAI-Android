package com.mobven.fitai.data.model.response

data class UserToken(
    val isFirstLogin: Boolean,
    val token: String
)