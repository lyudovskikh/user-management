package com.lyudovskikh.user.management.service

import com.lyudovskikh.user.management.mapper.UserInfoMapper
import com.lyudovskikh.user.management.model.dto.UserInfoCreationRequestDto
import com.lyudovskikh.user.management.model.dto.UserInfoDto
import com.lyudovskikh.user.management.repository.UserInfoRepository
import com.lyudovskikh.user.management.service.api.UserInfoService
import java.sql.SQLDataException

class UserInfoServiceImpl(
    private val mapper: UserInfoMapper,
    private val repository: UserInfoRepository,
): UserInfoService {

    override fun create(userInfoCreationRequest: UserInfoCreationRequestDto): UserInfoDto {
        if (repository.findByLogin(userInfoCreationRequest.login) != null) {
            throw IllegalArgumentException("User (login = ${userInfoCreationRequest.login}) is already exists!")
        }
        return mapper.map(repository.save(mapper.map(userInfoCreationRequest)))
    }

    override fun update(userInfo: UserInfoDto): UserInfoDto {
        val updatedUserInfo = repository.update(mapper.map(userInfo)) ?: throw SQLDataException("The record has been modified by another transaction!");
        return mapper.map(updatedUserInfo)
    }

    override fun delete(id: Long) {
        repository.deleteById(id)
    }

    override fun getById(id: Long): UserInfoDto {
        val userInfo = repository.findById(id) ?: throw NoSuchElementException("There is no user info with id = $id!");
        return mapper.map(userInfo)
    }

    override fun findAll(): List<UserInfoDto> {
        return repository.findAll().map { userInfo -> mapper.map(userInfo) }
    }
}