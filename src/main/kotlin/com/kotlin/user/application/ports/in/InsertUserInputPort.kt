package com.kotlin.user.application.ports.`in`

import com.kotlin.user.adapters.`in`.controller.request.UserRequest

interface InsertUserInputPort {
    fun insert(request: UserRequest)
}
