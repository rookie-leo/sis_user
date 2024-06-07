package com.kotlin.user.adapters.`in`.controller

import com.kotlin.user.adapters.exceptios.InvalidUserException
import com.kotlin.user.adapters.exceptios.UserNotFoundException
import com.kotlin.user.adapters.`in`.controller.request.UserLoginRequest
import com.kotlin.user.adapters.`in`.controller.request.UserRequest
import com.kotlin.user.application.ports.`in`.FindUserByEmailInputPort
import com.kotlin.user.application.ports.`in`.InsertUserInputPort
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val insertUserInputPort: InsertUserInputPort,
    private val findUserByEmail: FindUserByEmailInputPort
) {

    @PostMapping
    fun insert(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<Any> {
        return try {
            insertUserInputPort.insert(userRequest)
            return ResponseEntity.status(201).build()
        } catch (ex: InvalidUserException) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.message, ex)
        }
    }

    @PostMapping
    fun login(@Valid @RequestBody userLoginRequest: UserLoginRequest): ResponseEntity<Any> {
        try{ //TODO implementar a classe FindUserByEmailConfig
            return ResponseEntity.ok(findUserByEmail.find(userLoginRequest.email))
        } catch (ex: UserNotFoundException) {
            throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.message, ex)
        }
    }
}