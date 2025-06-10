package com.example.appwithfirebase.ui.task

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwithfirebase.data.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TaskViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val tasksCollection = db.collection("tasks")

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        listenToTasks()
    }

    private fun listenToTasks() {
        tasksCollection.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.e("Error", "Error loading Tasks")
                _error.value = "Error al cargar tareas: ${e.message}"
                _isLoading.value = false
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val taskList = mutableListOf<Task>()
                for (doc in snapshot.documents) {
                    val task = doc.toObject(Task::class.java)
                    task?.let {
                        taskList.add(it.copy(id = doc.id))
                    }
                }
                _tasks.value = taskList
                _isLoading.value = false
                _error.value = null
            } else {
                Log.d("Log1","Current data: null")
                _isLoading.value = false
            }
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val newTask = Task(title = title)
                tasksCollection.add(newTask).await()
                Log.d("Added","Task added successfully: $title")
                _error.value = null
            } catch (e: Exception) {
                Log.e("Error", "Error adding task.")
                _error.value = "Error al añadir tarea: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                tasksCollection.document(task.id)
                    .update("completed", !task.completed)
                    .await()
                Log.d("","Task completion toggled for: ${task.title}")
                _error.value = null
            } catch (e: Exception) {
                Log.e("", "Error toggling task completion.")
                _error.value = "Error al actualizar tarea: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                tasksCollection.document(task.id).delete().await()
                Log.d("","Task deleted: ${task.title}")
                _error.value = null
            } catch (e: Exception) {
                Log.e("", "Error deleting task.")
                _error.value = "Error al eliminar tarea: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}