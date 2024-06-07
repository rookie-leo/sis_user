package com.kotlin.user.adapters.config.security.utils

import com.kotlin.user.adapters.exceptios.AuthenticationException
import com.kotlin.user.adapters.out.repository.entity.UserEntity
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JWTUtils {

    @Value("\${jwt.secret}")
    private lateinit var jwtSecret: String

    @Value("\${jwt.expiration}")
    private var jwtExpirationMs: Int = 0

    fun generateJwtToken(userDetails: UserEntity): String =
        Jwts.builder()
            .setSubject((userDetails.email))
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()

    fun getUserNameFromJwtToken(token: String): String =
        Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
            .subject

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: Exception) {
            throw AuthenticationException("Falha ao efetuar o login! Verifique suas credenciais")
        }

        return false
    }
}