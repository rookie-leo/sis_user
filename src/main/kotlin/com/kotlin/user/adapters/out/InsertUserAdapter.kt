package com.kotlin.user.adapters.out

import com.kotlin.user.adapters.out.repository.UserRepository
import com.kotlin.user.adapters.out.repository.mapper.toEntity
import com.kotlin.user.application.core.domain.User
import com.kotlin.user.application.ports.out.InsertUserOutputPort
import org.springframework.stereotype.Component

@Component
class InsertUserAdapter(
    private val userRepository: UserRepository
) : InsertUserOutputPort {
    override fun insert(user: User) {
        userRepository.save(toEntity(user))
    }
}