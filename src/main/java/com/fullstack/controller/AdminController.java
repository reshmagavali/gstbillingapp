package com.fullstack.controller;

import com.fullstack.config.JWTUtil;
import com.fullstack.model.Admin;
import com.fullstack.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<Admin> signUp(@Valid @RequestBody Admin admin)
    {
        return ResponseEntity.ok(adminService.signUp(admin));
    }
    @PostMapping("/signin")
    public ResponseEntity<String> generatetoken(@RequestBody Admin admin)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getName(), admin.getPass()));
         return ResponseEntity.ok(jwtUtil.generateToken(admin.getName()));
    }


}
