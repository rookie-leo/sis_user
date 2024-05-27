package com.kotlin.user.adapters.out.repository

import com.kotlin.user.adapters.out.repository.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
}