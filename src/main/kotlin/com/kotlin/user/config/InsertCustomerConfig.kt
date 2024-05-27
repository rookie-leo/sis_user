package com.kotlin.user.config

import com.kotlin.user.adapters.out.InsertUserAdapter
import com.kotlin.user.application.core.usecase.InsertUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InsertCustomerConfig {

    @Bean
    fun insert(insertUserAdapter: InsertUserAdapter): InsertUserUseCase =
        InsertUserUseCase(insertUserAdapter)

}