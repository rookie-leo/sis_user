package com.kotlin.user.adapters.out.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(unique = false, nullable = false) val name: String,
    @Column(unique = true, nullable = false) val email: String,
    @Column(unique = false, nullable = false) val password: String
)