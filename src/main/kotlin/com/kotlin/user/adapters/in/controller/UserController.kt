package com.kotlin.user.adapters.`in`.controller

import com.kotlin.user.adapters.`in`.controller.request.UserRequest
import com.kotlin.user.application.ports.`in`.InsertUserInputPort
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val insertUserInputPort: InsertUserInputPort
) {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    fun insert(@Valid @RequestBody userRequest: UserRequest) =
        insertUserInputPort.insert(userRequest)

}