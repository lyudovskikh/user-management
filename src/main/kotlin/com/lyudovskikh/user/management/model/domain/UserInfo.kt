package com.lyudovskikh.user.management.model.domain

data class UserInfo(
    val id: Long,
    val login: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val version: Int
)
