package com.kotlin.user.adapters.config.security

import com.kotlin.user.adapters.config.security.utils.JWTUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class JwtAuthTokenFilter(
    @Autowired private val jwtUtils: JWTUtils,
//    @Autowired private val user
) {
}