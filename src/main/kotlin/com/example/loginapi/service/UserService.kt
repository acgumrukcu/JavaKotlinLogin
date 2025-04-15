package com.example.loginapi.service

import com.example.loginapi.model.User
import com.example.loginapi.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun login(username: String, rawPassword: String): Optional<User> {
        val user = userRepository.findByUsername(username)
        return if(user.isPresent && passwordEncoder.matches(rawPassword, user.get().password))
        {
           user
        } else {
            Optional.empty()
        }
    }

    fun register(user: User): User {
        val hashedPassword = passwordEncoder.encode(user.password)
        println("Orginal password, ${hashedPassword}")
        val newUser = user.copy(password = hashedPassword)
        println("Orginal password, ${newUser}")
        return userRepository.save(newUser)
    }
}