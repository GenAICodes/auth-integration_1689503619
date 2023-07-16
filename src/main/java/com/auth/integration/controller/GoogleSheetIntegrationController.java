
package com.auth.integration.controller;

import com.auth.integration.model.User;
import com.auth.integration.service.GoogleSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/google-sheet")
public class GoogleSheetIntegrationController {
    private final GoogleSheetService googleSheetService;

    @Autowired
    public GoogleSheetIntegrationController(GoogleSheetService googleSheetService) {
        this.googleSheetService = googleSheetService;
    }

    @PostMapping("/store-user-email")
    public void storeUserEmail(@RequestBody User user) {
        googleSheetService.storeUserEmail(user);
    }
}
