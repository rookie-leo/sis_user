package com.kotlin.user.adapters.exceptios

class AuthenticationException(
    errorMessage: String? = null
) : RuntimeException(errorMessage)