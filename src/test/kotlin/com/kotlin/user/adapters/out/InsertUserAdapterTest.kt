package com.kotlin.user.adapters.out

import com.kotlin.user.adapters.exceptios.InvalidUserException
import com.kotlin.user.adapters.out.repository.UserRepository
import com.kotlin.user.adapters.out.repository.entity.UserEntity
import com.kotlin.user.adapters.out.repository.mapper.toEntity
import com.kotlin.user.application.core.domain.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class InsertUserAdapterTest {

    private lateinit var userRepository: UserRepository
    private lateinit var insertUserAdapter: InsertUserAdapter

    @BeforeEach
    fun setup() {
        userRepository = mock(UserRepository::class.java)
        insertUserAdapter = InsertUserAdapter(userRepository)
    }

    @Test
    fun `should insert user in database`() {
        val user = User("Fulano", "fulano@email.com", "123456")
        val entity = toEntity(user)

        `when`(userRepository.save(any(UserEntity::class.java))).thenReturn(entity)

        insertUserAdapter.insert(user)

        verify(userRepository, times(1)).save(any(UserEntity::class.java))
        assertEquals(user.name, entity.name)
        assertEquals(user.email, entity.email)
        assertEquals(user.password, entity.password)
    }

    @Test
    fun `should not insert invalid user in database`() {
        val invalidUser = User("", "", "")
        val entity = toEntity(invalidUser)

        `when`(userRepository.save(entity)).thenThrow(NullPointerException::class.java)

        assertThrows<NullPointerException> {
            insertUserAdapter.insert(invalidUser)
        }
        verify(userRepository, times(1)).save(any(UserEntity::class.java))
    }


    @Test
    fun `should not insert user with an email that is already in the database`() {
        val user = User("Fulano", "fulano@email.com", "123456")
        val invalidUser = User("Fulano", "fulano@email.com", "123456")

        `when`(userRepository.save(toEntity(user))).thenReturn(toEntity(user))
        `when`(userRepository.save(toEntity(invalidUser)))
            .thenThrow(InvalidUserException::class.java)

        assertThrows<InvalidUserException>{
            insertUserAdapter.insert(invalidUser)
        }
    }
}