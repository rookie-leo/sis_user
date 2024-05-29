package com.kotlin.user.adapters.exceptios

class InvalidUserException(
    errorMessage: String? = null
) : RuntimeException(errorMessage)
