package com.lyudovskikh.user.management.controller

import com.lyudovskikh.user.management.model.dto.UserInfoCreationRequestDto
import com.lyudovskikh.user.management.model.dto.UserInfoDto
import com.lyudovskikh.user.management.service.api.UserInfoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.core.MediaType.APPLICATION_JSON


@Api("User Info Controller")
@Path("/users")
class UserInfoController(private val userInfoService: UserInfoService) {

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation("Create user info")
    fun create(@Valid @ApiParam("User info creation request", required = true) userInfoCreationRequest: UserInfoCreationRequestDto): UserInfoDto {
        return userInfoService.create(userInfoCreationRequest)
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    @ApiOperation("Get user info by ID")
    fun getById(@ApiParam("User info ID", required = true) @PathParam("id") id: Long): UserInfoDto {
        return userInfoService.getById(id)
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation("Update user info")
    fun update(@Valid @ApiParam("User info", required = true) userInfo: UserInfoDto): UserInfoDto {
        return userInfoService.update(userInfo);
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Delete user info")
    fun delete(@ApiParam("User info ID", required = true) @PathParam("id") id: Long) {
        return userInfoService.delete(id)
    }

    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation("Find all users")
    fun findAll(): List<UserInfoDto> {
        return userInfoService.findAll()
    }

}