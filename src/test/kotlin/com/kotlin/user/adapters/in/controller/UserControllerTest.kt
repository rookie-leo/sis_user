package com.kotlin.user.adapters.`in`.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlin.user.adapters.exceptios.InvalidUserException
import com.kotlin.user.adapters.`in`.controller.request.UserRequest
import com.kotlin.user.application.ports.`in`.InsertUserInputPort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
@ContextConfiguration
@AutoConfigureMockMvc(addFilters = false)
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

        verify(insertUserInputPort, times(1)).insert(request)
    }

    @Test
    fun `should not insert user and return BAD_REQUEST status`() {
        val request = UserRequest("", "email@email.com", "123456")

        doNothing().`when`(insertUserInputPort).insert(request)

        mockMvc.perform(
            post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isBadRequest)

        verify(insertUserInputPort, times(0)).insert(request)
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

        verify(insertUserInputPort, times(1)).insert(userRequest)
    }
}