package com.example.appforautotesting.repository

import kotlinx.coroutines.delay

class UserRepositoryImpl : UserRepository {
    override suspend fun getUsers(): List<String> {
        // Simulación de una llamada de red o a base de datos
        delay(1000)
        return listOf("Alice", "Bob", "Charlie")
    }
}
