
package com.auth.integration.mapper;

import com.auth.integration.dto.UserDTO;
import com.auth.integration.model.User;
import org.springframework.stereotype.Component;

@Component
public class GoogleSheetMapper {
    public User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
