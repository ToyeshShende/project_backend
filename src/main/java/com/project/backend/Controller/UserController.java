package com.project.backend.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.Dto.LoginDTO;
import com.project.backend.Dto.UserDTO;
import com.project.backend.Entity.User;
import com.project.backend.Service.UserService;
import com.project.backend.response.LoginResponse;

@RestController
@CrossOrigin
@RequestMapping
public class UserController {
    
    @Autowired
    private UserService userService;


    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO)
    {
        String result = userService.addUser(userDTO);
        return result;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    
    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("password");

        String result = userService.updatePassword(newPassword, email);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user-details")
    public ResponseEntity<?> getUserDetails(@RequestParam String email) {
        Optional<UserDTO> userDetails = userService.getUserDetails(email);

        if (userDetails.isPresent()) {
            return ResponseEntity.ok(userDetails.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User details not found for the given email.");
        }
    }
}
