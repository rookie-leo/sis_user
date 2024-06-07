package com.kotlin.user.application.ports.`in`

import com.kotlin.user.application.core.domain.User

interface FindUserByEmailInputPort {
    fun find(email: String): User
}
