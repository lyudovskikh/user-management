package com.lyudovskikh.user.management.model.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

@ApiModel(description = "User info")
data class UserInfoDto(

    @field:NotNull
    @field:Positive
    @ApiModelProperty(value = "ID", example = "1")
    val id: Long,

    @field:NotBlank
    @field:Size(min = 1, max = 50)
    @ApiModelProperty(value = "Login", example = "lyudovskikh")
    val login: String,

    @field:NotBlank
    @field:Size(min = 1, max = 50)
    @field:Email
    @ApiModelProperty(value = "Email", example = "dmitry.lyudovskikh@gmail.com")
    val email: String,

    @field:NotBlank
    @field:Size(min = 1, max = 100)
    @ApiModelProperty(value = "First name", example = "Dmitry")
    val firstName: String,

    @field:NotBlank
    @field:Size(min = 1, max = 100)
    @ApiModelProperty(value = "Last name", example = "Lyudovskikh")
    val lastName: String,

    @field:NotBlank
    @field:Size(min = 1, max = 100)
    @ApiModelProperty(value = "Middle name", example = "Sergeevich")
    val middleName: String,

    @field:Positive
    @ApiModelProperty(value = "Entity version", example = "1")
    val version: Int
)