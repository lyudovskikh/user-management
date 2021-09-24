package com.lyudovskikh.user.management.model.domain

data class UserInfoCreationRequest(
    val login: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
)
