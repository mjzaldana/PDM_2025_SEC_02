package com.example.appforautotesting.repository

data class UserDetail(
    val id: Int,
    val name: String,
    val isFavorite: Boolean
)

interface UserDetailRepository {
    suspend fun fetchUserDetail(userId: Int): UserDetail
    suspend fun toggleFavorite(userId: Int): UserDetail
}
