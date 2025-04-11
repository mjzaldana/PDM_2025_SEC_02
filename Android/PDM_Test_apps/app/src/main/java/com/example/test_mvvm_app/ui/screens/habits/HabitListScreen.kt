package com.example.test_mvvm_app.ui.screens.habits

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.items
import com.example.test_mvvm_app.ui.viewmodels.HabitViewModel

@Composable
fun HabitListScreen(
    navController: NavController,
    viewModel: HabitViewModel
){
    val habits by viewModel.allHabits.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) {
        padding ->
        LazyColumn (
            modifier = Modifier.padding(padding)
        ){
            items(habits){
                habit ->

            }
        }
    }
}