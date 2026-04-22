package com.example.demo.controller;

import com.example.demo.service.PasskeyUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passkey")
public class AuthController {

    private final PasskeyUserService userService;

    public AuthController(PasskeyUserService userService) {
        this.userService = userService;
    }

}