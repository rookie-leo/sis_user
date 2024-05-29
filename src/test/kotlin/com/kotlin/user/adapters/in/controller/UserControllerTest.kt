package com.kotlin.user.adapters.`in`.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlin.user.adapters.exceptios.InvalidUserException
import com.kotlin.user.adapters.`in`.controller.request.UserRequest
import com.kotlin.user.application.ports.`in`.InsertUserInputPort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.doThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var insertUserInputPort: InsertUserInputPort

    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setup() {
        objectMapper = ObjectMapper()
    }

    @Test
    fun `should insert user and return CREATED status`() {
        val request = UserRequest("Fulano", "fulano@email.com", "123456")

        doNothing().`when`(insertUserInputPort).insert(request)

        mockMvc.perform(
            post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
    }

    @Test
    fun `shouold not insert user and return BAD_REQUEST status`() {
        val request = UserRequest("", "email@email.com", "123456")

        doNothing().`when`(insertUserInputPort).insert(request)

        mockMvc.perform(
            post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `should return 422 status and error message when InvalidUserException is thrown`() {
        val userRequest = UserRequest("Fulano", "fulano@email.com", "123456")
        val errorMessage = "Tentativa de inserir usuario invalida!"
        val expectedMessage = "{\"status\":422,\"message\":\"Tentativa de inserir usuario invalida!\"}"

        doThrow(InvalidUserException(errorMessage)).`when`(insertUserInputPort).insert(userRequest)

        mockMvc.perform(
            post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isUnprocessableEntity)
            .andExpect(content().string(expectedMessage))
    }
}