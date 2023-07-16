
package com.auth.integration.controller;

import com.auth.integration.dto.UserDTO;
import com.auth.integration.mapper.GoogleSheetMapper;
import com.auth.integration.model.User;
import com.auth.integration.service.GoogleSheetIntegrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoogleSheetIntegrationService googleSheetIntegrationService;

    @MockBean
    private GoogleSheetMapper googleSheetMapper;

    @InjectMocks
    private UserController userController;

    @Test
    public void testSignup() throws Exception {
        UserDTO userDTO = new UserDTO("test@example.com");
        User user = new User();
        user.setEmail("test@example.com");

        when(googleSheetMapper.mapToUser(userDTO)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(googleSheetIntegrationService).storeUserEmail(user);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
