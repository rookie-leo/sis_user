package com.kotlin.user.adapters.exceptios.handler

import com.kotlin.user.adapters.exceptios.InvalidUserException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionController {

    @ExceptionHandler
    fun handleInvalidUserException(ex: InvalidUserException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            ex.message
            )
        return ResponseEntity(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY)
    }

}