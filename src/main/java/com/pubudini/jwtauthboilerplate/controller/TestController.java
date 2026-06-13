package com.pubudini.jwtauthboilerplate.controller;

import com.pubudini.jwtauthboilerplate.dto.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/user/profile")
    public ResponseEntity<MessageResponse> userProfile() {
        return ResponseEntity.ok(new MessageResponse("Welcome! You have USER access."));
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> adminDashboard() {
        return ResponseEntity.ok(new MessageResponse("Welcome Admin! You have ADMIN access."));
    }
}