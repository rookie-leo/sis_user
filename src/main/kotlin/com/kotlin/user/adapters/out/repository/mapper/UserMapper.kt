package com.kotlin.user.adapters.out.repository.mapper

import com.kotlin.user.adapters.out.repository.entity.UserEntity
import com.kotlin.user.application.core.domain.User

fun User.toUser(userEntity: UserEntity): User {
    return User(
        name = userEntity.name,
        email = userEntity.email,
        password = userEntity.password
    )
}

fun toEntity(user: User): UserEntity {
    return UserEntity(
        name = user.name,
        email = user.email,
        password = user.password
    )
}
