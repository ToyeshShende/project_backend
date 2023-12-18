package com.project.backend.Service;

import java.util.Optional;

import com.project.backend.Dto.LoginDTO;
import com.project.backend.Dto.UserDTO;
import com.project.backend.response.LoginResponse;

public interface UserService {

    String addUser(UserDTO userDTO);

    LoginResponse loginUser(LoginDTO loginDTO);
    
    String updatePassword(String newPassword, String email);

    Optional<UserDTO> getUserDetails(String email);
}
