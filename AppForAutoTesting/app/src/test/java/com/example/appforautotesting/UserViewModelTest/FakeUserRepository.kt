package com.example.appforautotesting.UserViewModelTest

import com.example.appforautotesting.repository.UserRepository

class FakeUserRepository: UserRepository {
    override suspend fun getUsers(): List<String>{
        return listOf("Alice", "Bob", "Charlie")
    }
}