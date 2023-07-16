
package com.auth.integration.service;

import com.auth.integration.model.User;
import com.auth.integration.repository.GoogleSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleSheetIntegrationService {
    private final GoogleSheetRepository googleSheetRepository;

    @Autowired
    public GoogleSheetIntegrationService(GoogleSheetRepository googleSheetRepository) {
        this.googleSheetRepository = googleSheetRepository;
    }

    public void storeUserEmail(User user) {
        googleSheetRepository.save(user);
    }
}
