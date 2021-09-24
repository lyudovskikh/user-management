package com.lyudovskikh.user.management.service.api

import com.lyudovskikh.user.management.model.dto.UserInfoCreationRequestDto
import com.lyudovskikh.user.management.model.dto.UserInfoDto

interface UserInfoService {

    /**
     * Create user info
     * @param userInfoCreationRequest user info creation request
     * @return created user info
     */
    fun create(userInfoCreationRequest: UserInfoCreationRequestDto): UserInfoDto

    /**
     * Update user info
     * @param userInfo user info update
     * @return updated user info
     */
    fun update(userInfo: UserInfoDto): UserInfoDto

    /**
     * Delete user info
     * @param id user info identifier
     */
    fun delete(id: Long)

    /**
     * Receive user info by identifier
     * @param id identifier
     * @return user info
     */
    fun getById(id: Long): UserInfoDto

    /**
     * Find all users
     * @return users
     */
    fun findAll(): List<UserInfoDto>

}