package com.example.appforautotesting.Screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.appforautotesting.viewModel.UserViewModel
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserScreen(viewModel: UserViewModel) {
    val users by viewModel.users.collectAsState()

    LaunchedEffect (Unit) {
        viewModel.loadUsers()
    }

    LazyColumn {
        items(users) { user ->
            Text(text = user, modifier = Modifier.padding(16.dp))
        }
    }
}
