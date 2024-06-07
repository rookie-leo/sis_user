package com.kotlin.user.adapters.out

import com.kotlin.user.adapters.exceptios.UserNotFoundException
import com.kotlin.user.adapters.`in`.controller.response.UserResponse
import com.kotlin.user.adapters.out.repository.UserRepository
import com.kotlin.user.adapters.out.repository.mapper.toUserResponse
import com.kotlin.user.application.ports.out.FindUserByEmailOutPutPort
import org.springframework.stereotype.Component

@Component
class FindUserByEmailAdapter(
    private val userRepository: UserRepository
) : FindUserByEmailOutPutPort {
    override fun find(userEmail: String): UserResponse {
        return try {
            toUserResponse(userRepository.findByEmail(userEmail))
        } catch (ex: Exception) {
            throw UserNotFoundException()
        }
    }
}