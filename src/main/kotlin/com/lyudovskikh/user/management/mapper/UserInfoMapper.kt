package com.lyudovskikh.user.management.mapper

import com.lyudovskikh.user.management.model.domain.UserInfo
import com.lyudovskikh.user.management.model.domain.UserInfoCreationRequest
import com.lyudovskikh.user.management.model.dto.UserInfoCreationRequestDto
import com.lyudovskikh.user.management.model.dto.UserInfoDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

/**
 * User info mapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface UserInfoMapper {
    /**
     * Map domain user info model to dto user info model
     * @param userInfo domain user info model
     * @return dto user info model
     */
    fun map(userInfo: UserInfo): UserInfoDto

    /**
     * Map dto user info model to domain user info model
     * @param userInfo dto user info model
     * @return domain user info model
     */
    fun map(userInfo: UserInfoDto): UserInfo

    /**
     * Map domain user info creation request model to dto user info creation request model
     * @param userInfoCreationRequest domain user info creation request model
     * @return dto user info creation request model
     */
    fun map(userInfoCreationRequest: UserInfoCreationRequest): UserInfoCreationRequestDto

    /**
     * Map dto user info creation request model to domain user info creation request model
     * @param userInfoCreationRequest dto user info creation request model
     * @return domain user info creation request model
     */
    fun map(userInfoCreationRequest: UserInfoCreationRequestDto): UserInfoCreationRequest
}