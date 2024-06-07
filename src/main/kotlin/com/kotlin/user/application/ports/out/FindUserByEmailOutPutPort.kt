package com.kotlin.user.application.ports.out

import com.kotlin.user.adapters.`in`.controller.response.UserResponse

interface FindUserByEmailOutPutPort {
    fun find(userEmail: String): UserResponse
}
