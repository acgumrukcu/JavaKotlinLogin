package com.example.loginapi.controller

import com.example.loginapi.model.User
import com.example.loginapi.security.JwtUtil
import com.example.loginapi.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class LoginController(private val userService: UserService, private val jwtUtil: JwtUtil) {


    @PostMapping("/login")
    fun login(@RequestBody user: User): ResponseEntity<String> {
        val result = userService.login(user.username, user.password)
        return if (result.isPresent) {
            val token = jwtUtil.generateToken(user.username)
            ResponseEntity.ok(token)
        } else {
            ResponseEntity.status(401).body("Invalid credentials")
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<String> {
        val savedUser = userService.register(user)
        return ResponseEntity.ok("User registered with ID: ${savedUser.id}")
    }
}