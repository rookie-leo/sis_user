package com.kotlin.user.adapters.`in`.controller.request

import com.kotlin.user.application.core.domain.User
import jakarta.validation.constraints.NotBlank

data class UserRequest(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val email: String,

    @field:NotBlank
    val password: String
) {
    fun toUser(): User {
        return User(
            name = this.name,
            email = this.email,
            password = this.password
        )
    }
}