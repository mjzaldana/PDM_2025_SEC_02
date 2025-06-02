package com.example.appforautotesting.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appforautotesting.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel(){
    private val _users = MutableStateFlow<List<String>>(emptyList())
    val users: StateFlow<List<String>> = _users.asStateFlow()

    //Al ser llamado cargue a todos los usuarios
    fun loadUsers(){
        viewModelScope.launch {
            try {
                _users.value = userRepository.getUsers()
            }catch (e: Exception){
                _users.value = emptyList<String>()
            }

        }
    }
}
