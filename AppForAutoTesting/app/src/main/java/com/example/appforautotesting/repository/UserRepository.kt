package com.example.appforautotesting.repository

interface UserRepository {
    suspend fun getUsers(): List<String>
}
