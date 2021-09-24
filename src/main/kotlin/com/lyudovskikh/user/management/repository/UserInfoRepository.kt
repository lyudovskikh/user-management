package com.lyudovskikh.user.management.repository

import com.lyudovskikh.user.management.model.domain.UserInfo
import com.lyudovskikh.user.management.model.domain.UserInfoCreationRequest
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface UserInfoRepository {

    @SqlUpdate("INSERT INTO USER_INFO(LOGIN, EMAIL, FIRST_NAME, LAST_NAME, MIDDLE_NAME) " +
            "VALUES (:userInfoCreationRequest.login, :userInfoCreationRequest.email, :userInfoCreationRequest.firstName, :userInfoCreationRequest.lastName, :userInfoCreationRequest.middleName)")
    @GetGeneratedKeys("ID", "LOGIN", "EMAIL", "FIRST_NAME", "LAST_NAME", "MIDDLE_NAME", "VERSION")
    fun save(userInfoCreationRequest: UserInfoCreationRequest): UserInfo

    @SqlUpdate("DELETE FROM USER_INFO WHERE ID = ?")
    fun deleteById(id: Long)

    @SqlUpdate("UPDATE USER_INFO SET " +
            "LOGIN = :userInfo.login, " +
            "EMAIL = :userInfo.email, " +
            "FIRST_NAME = :userInfo.firstName, " +
            "LAST_NAME = :userInfo.lastName, " +
            "MIDDLE_NAME = :userInfo.middleName, " +
            "VERSION = :userInfo.version + 1 " +
            "WHERE ID = :userInfo.id AND VERSION = :userInfo.version")
    @GetGeneratedKeys("ID", "LOGIN", "EMAIL", "FIRST_NAME", "LAST_NAME", "MIDDLE_NAME", "VERSION")
    fun update(userInfo: UserInfo): UserInfo?

    @SqlQuery("SELECT * FROM USER_INFO WHERE ID = ?")
    fun findById(id: Long): UserInfo?

    @SqlQuery("SELECT * FROM USER_INFO WHERE LOGIN = ?")
    fun findByLogin(login: String): UserInfo?

    @SqlQuery("SELECT * FROM USER_INFO")
    fun findAll(): List<UserInfo>

}