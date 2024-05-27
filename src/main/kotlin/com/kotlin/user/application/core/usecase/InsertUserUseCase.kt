package com.kotlin.user.application.core.usecase

import com.kotlin.user.adapters.`in`.controller.request.UserRequest
import com.kotlin.user.application.ports.`in`.InsertUserInputPort
import com.kotlin.user.application.ports.out.InsertUserOutputPort

class InsertUserUseCase(
    private val insertUserOutputPort: InsertUserOutputPort
) : InsertUserInputPort {
    override fun insert(request: UserRequest) {
        insertUserOutputPort.insert(request.toUser())
    }
}