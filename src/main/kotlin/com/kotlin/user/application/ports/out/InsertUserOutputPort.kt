package com.kotlin.user.application.ports.out

import com.kotlin.user.application.core.domain.User

interface InsertUserOutputPort {
    fun insert(user: User)
}
