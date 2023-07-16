
package com.auth.integration.controller;

import com.auth.integration.dto.UserDTO;
import com.auth.integration.mapper.GoogleSheetMapper;
import com.auth.integration.model.User;
import com.auth.integration.service.GoogleSheetIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final GoogleSheetIntegrationService googleSheetIntegrationService;
    private final GoogleSheetMapper googleSheetMapper;

    @Autowired
    public UserController(GoogleSheetIntegrationService googleSheetIntegrationService, GoogleSheetMapper googleSheetMapper) {
        this.googleSheetIntegrationService = googleSheetIntegrationService;
        this.googleSheetMapper = googleSheetMapper;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody UserDTO userDTO) {
        User user = googleSheetMapper.mapToUser(userDTO);
        googleSheetIntegrationService.storeUserEmail(user);
    }
}
