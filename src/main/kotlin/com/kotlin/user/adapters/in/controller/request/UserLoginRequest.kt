package com.kotlin.user.adapters.`in`.controller.request

import com.kotlin.user.adapters.`in`.controller.response.UserResponse
import com.kotlin.user.application.core.domain.User
import jakarta.validation.constraints.NotBlank

data class UserLoginRequest (

    @NotBlank
    val email: String,

    @NotBlank
    val password: String
)