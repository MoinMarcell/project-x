package com.github.moinmarcell.projectxbackend.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String BASE_URL = "/api/v1/auth";

    @Test
    @DirtiesContext
    void register_whenRegisterSuccess_expectStatus200AndBodyOfRegisterResponse() throws Exception {

        var userToRegister = RegisterRequest.builder()
                .username("test")
                .email("test@test.de")
                .password("test")
                .build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/register")
                        .with(csrf())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userToRegister)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        RegisterResponse savedUser = objectMapper.readValue(body, RegisterResponse.class);

        assertEquals(userToRegister.getUsername(), savedUser.getUsername());
        assertNotNull(savedUser.getCreatedAt());
        assertEquals("User successfully registered", savedUser.getSuccessMessage());

    }

    @Test
    @DirtiesContext
    void register_whenUsernameAlreadyExist_expectStatus500() throws Exception {

        var userToRegister = RegisterRequest.builder()
                .username("test")
                .email("test@test.de")
                .password("test")
                .build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/register")
                        .with(csrf())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userToRegister)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        RegisterResponse savedUser = objectMapper.readValue(body, RegisterResponse.class);

        assertEquals(userToRegister.getUsername(), savedUser.getUsername());
        assertNotNull(savedUser.getCreatedAt());
        assertEquals("User successfully registered", savedUser.getSuccessMessage());

    }

    @Test
    void login() {
    }
}