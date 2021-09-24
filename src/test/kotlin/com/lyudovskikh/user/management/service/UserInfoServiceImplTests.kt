package com.lyudovskikh.user.management.service

import com.lyudovskikh.user.management.mapper.UserInfoMapper
import com.lyudovskikh.user.management.model.domain.UserInfo
import com.lyudovskikh.user.management.model.domain.UserInfoCreationRequest
import com.lyudovskikh.user.management.model.dto.UserInfoCreationRequestDto
import com.lyudovskikh.user.management.model.dto.UserInfoDto
import com.lyudovskikh.user.management.repository.UserInfoRepository
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.junit.Assert.assertSame
import org.junit.Assert.assertThrows
import org.junit.Test
import org.mockito.Mockito
import java.sql.SQLDataException
import java.util.*

class UserInfoServiceImplTests {

    @Test
    fun whenCreateNewUserInfo_thenUserInfoShouldBeCreated() {
        val mapper = Mockito.mock(UserInfoMapper::class.java)
        val repository = Mockito.mock(UserInfoRepository::class.java)
        val userInfoService = UserInfoServiceImpl(mapper, repository)

        val userInfoCreationRequestDto = buildUserInfoCreationRequestDto()
        val userInfoCreationRequest = Mockito.mock(UserInfoCreationRequest::class.java)
        val createdUserInfo = Mockito.mock(UserInfo::class.java)
        val expected = Mockito.mock(UserInfoDto::class.java)

        Mockito.`when`(repository.findByLogin(userInfoCreationRequestDto.login)).thenReturn(null)
        Mockito.`when`(mapper.map(userInfoCreationRequestDto)).thenReturn(userInfoCreationRequest)
        Mockito.`when`(repository.save(userInfoCreationRequest)).thenReturn(createdUserInfo)
        Mockito.`when`(mapper.map(createdUserInfo)).thenReturn(expected)

        val actual: UserInfoDto = userInfoService.create(userInfoCreationRequestDto)

        assertSame(expected, actual)
    }

    @Test
    fun whenCreateExistingUserInfo_thenIllegalArgumentExceptionShouldBeThrown() {
        val mapper = Mockito.mock(UserInfoMapper::class.java)
        val repository = Mockito.mock(UserInfoRepository::class.java)
        val userInfoService = UserInfoServiceImpl(mapper, repository)

        val userInfoCreationRequestDto = buildUserInfoCreationRequestDto()
        val userInfo = Mockito.mock(UserInfo::class.java)

        Mockito.`when`(repository.findByLogin(userInfoCreationRequestDto.login)).thenReturn(userInfo)

        assertThrows(IllegalArgumentException::class.java) { userInfoService.create(userInfoCreationRequestDto) }
    }

    @Test
    fun whenUpdateExistingUserInfo_thenUserInfoShouldBeUpdated() {
        val mapper = Mockito.mock(UserInfoMapper::class.java)
        val repository = Mockito.mock(UserInfoRepository::class.java)
        val userInfoService = UserInfoServiceImpl(mapper, repository)

        val expected = buildUserInfoDto()
        val userInfo = Mockito.mock(UserInfo::class.java)
        val updatedUserInfo = Mockito.mock(UserInfo::class.java)

        Mockito.`when`(mapper.map(expected)).thenReturn(userInfo)
        Mockito.`when`(repository.update(userInfo)).thenReturn(updatedUserInfo)
        Mockito.`when`(mapper.map(updatedUserInfo)).thenReturn(expected)

        val actual = userInfoService.update(expected)

        assertSame(expected, actual)
    }

    @Test
    fun whenUpdateOldUserInfo_thenSQLDataExceptionShouldBeThrown() {
        val mapper = Mockito.mock(UserInfoMapper::class.java)
        val repository = Mockito.mock(UserInfoRepository::class.java)
        val userInfoService = UserInfoServiceImpl(mapper, repository)

        val userInfoDto = buildUserInfoDto()
        val userInfo = Mockito.mock(UserInfo::class.java)

        Mockito.`when`(mapper.map(userInfoDto)).thenReturn(userInfo)
        Mockito.`when`(repository.update(userInfo)).thenReturn(null)

        assertThrows(SQLDataException::class.java) { userInfoService.update(userInfoDto) }
    }

    @Test
    fun whenDeleteExistingUserInfo_thenUserInfoShouldBeDeleted() {
        val mapper = Mockito.mock(UserInfoMapper::class.java)
        val repository = Mockito.mock(UserInfoRepository::class.java)
        val userInfoService = UserInfoServiceImpl(mapper, repository)

        val userInfoId = 777L
        userInfoService.delete(userInfoId)
        Mockito.verify(repository).deleteById(userInfoId)
    }

    @Test
    fun whenGetByIdWithExistingUserInfo_thenUserInfoShouldBeReturned() {
        val mapper = Mockito.mock(UserInfoMapper::class.java)
        val repository = Mockito.mock(UserInfoRepository::class.java)
        val userInfoService = UserInfoServiceImpl(mapper, repository)

        val userInfoId = 777L
        val userInfo = Mockito.mock(UserInfo::class.java)
        val expected = Mockito.mock(UserInfoDto::class.java)

        Mockito.`when`(repository.findById(userInfoId)).thenReturn(userInfo)
        Mockito.`when`(mapper.map(userInfo)).thenReturn(expected)

        val actual = userInfoService.getById(userInfoId)
        assertSame(expected, actual)
    }

    @Test
    fun whenGetByIdWithoutExistingUserInfo_thenNoSuchElementExceptionShouldBeThrown() {
        val mapper = Mockito.mock(UserInfoMapper::class.java)
        val repository = Mockito.mock(UserInfoRepository::class.java)
        val userInfoService = UserInfoServiceImpl(mapper, repository)

        val userInfoId = 1L
        Mockito.`when`(repository.findById(userInfoId)).thenReturn(null)
        assertThrows(NoSuchElementException::class.java) { userInfoService.getById(userInfoId) }
    }

    private fun buildUserInfoDto(): UserInfoDto {
        return UserInfoDto(
            RandomUtils.nextLong(1, 1000000),
            RandomStringUtils.randomAlphanumeric(1, 25),
            RandomStringUtils.randomAlphanumeric(10) + "@mail.com",
            RandomStringUtils.randomAlphabetic(1, 25),
            RandomStringUtils.randomAlphabetic(1, 25),
            RandomStringUtils.randomAlphabetic(1, 25),
            RandomUtils.nextInt(1, 100)
        )
    }

    private fun buildUserInfoCreationRequestDto(): UserInfoCreationRequestDto {
        return UserInfoCreationRequestDto(
            RandomStringUtils.randomAlphanumeric(1, 25),
            RandomStringUtils.randomAlphanumeric(10) + "@mail.com",
            RandomStringUtils.randomAlphabetic(1, 25),
            RandomStringUtils.randomAlphabetic(1, 25),
            RandomStringUtils.randomAlphabetic(1, 25)
        )
    }

}