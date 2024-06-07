package com.kotlin.user.adapters.exceptios

class UserNotFoundException(
    errorMessage: String? = "Usuario não encontrado! Verifique seu email!"
) : RuntimeException(errorMessage)