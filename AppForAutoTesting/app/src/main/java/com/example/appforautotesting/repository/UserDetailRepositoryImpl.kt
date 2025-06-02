package com.example.appforautotesting.repository

import kotlinx.coroutines.delay

class UserDetailRepositoryImpl : UserDetailRepository {
    private val userDatabase = mutableMapOf(
        1 to UserDetail(id = 1, name = "Alice", isFavorite = false),
        2 to UserDetail(id = 2, name = "Bob", isFavorite = true)
    )

    override suspend fun fetchUserDetail(userId: Int): UserDetail {
        delay(100) // Simula
        return userDatabase[userId] ?: throw Exception("User not found")
    }

    override suspend fun toggleFavorite(userId: Int): UserDetail {
        delay(50) // Simula
        val current = userDatabase[userId] ?: throw Exception("User not found")
        val updated = current.copy(isFavorite = !current.isFavorite)
        userDatabase[userId] = updated
        return updated
    }
}
