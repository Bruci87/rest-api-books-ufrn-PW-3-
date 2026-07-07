package com.ufrn.pw.controller;

import com.ufrn.pw.core.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login() {
        String token = tokenService.gerarToken("admin_taniro");
        return ResponseEntity.ok(Map.of("token", token));
    }
}