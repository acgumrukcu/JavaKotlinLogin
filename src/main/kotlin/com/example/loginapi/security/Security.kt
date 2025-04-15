package com.example.loginapi.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    private val jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    private val expirationMs = 3600000 // 1 saat

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationMs))
            .signWith(jwtSecret)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun extractUsername(token: String): String {
        val claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token)
        return claims.body.subject
    }
}