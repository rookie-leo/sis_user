package com.kotlin.user.adapters.out

import com.kotlin.user.adapters.exceptios.InvalidUserException
import com.kotlin.user.adapters.out.repository.UserRepository
import com.kotlin.user.adapters.out.repository.mapper.toEntity
import com.kotlin.user.application.core.domain.User
import com.kotlin.user.application.ports.out.InsertUserOutputPort
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class InsertUserAdapter(
    private val userRepository: UserRepository
) : InsertUserOutputPort {
    override fun insert(user: User) {
        try {
            val encoder = BCryptPasswordEncoder()
            val encodedPasswor = encoder.encode(user.password)
            val userToSave = user.copy(password = encodedPasswor)
            userRepository.save(toEntity(userToSave))
        } catch (ex: DataIntegrityViolationException) {
            throw InvalidUserException("Tentativa de inserir usuario invalida!")
        }
    }
}