package com.example.loginapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class HelloController {
    @GetMapping("/hello")
    fun hello(): String {
        return "Merhaba güvenli alana hoş geldin"
    }
}