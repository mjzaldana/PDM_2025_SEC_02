package com.example.myapplication.LayoutExample3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

data class Project(
    val id: Int,
    val title: String,
    val description: String,
    var isCompleted: Boolean = false
)

@Composable
fun ProjectCard(
    project: Project,
    onComplete: (Project) -> Unit,
    onDelete: (Project) -> Unit,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier.padding(8.dp),
    ) {
        Column {
            Text(text = project.title)
            Text(text = project.description)
            Row {
                Button(onClick = { onComplete(project) }) {
                    Text(text = "Completar")
                }
                Button(onClick = { onDelete(project) }) {
                    Text(text = "Eliminar")
                }
            }
        }
    }
}

@Composable
fun NewProjectDialog(
    projects: MutableList<Project>,
    onDismiss: () -> Unit,
    onAddProject: (Project) -> Unit
) {
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo proyecto") },
        text = {
            Column {
                TextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    label = { Text("Título") }
                )
                TextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text("Descripción") }
                )
            }
        },
        confirmButton = {
            TextButton (onClick = {
                val newProject = Project(
                    id = projects.size,
                    title = title.value,
                    description = description.value
                )
                onAddProject(newProject)
            }) {
                Text("Agregar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun ProjectScreen() {
    val projects = remember { mutableStateListOf<Project>() }
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog.value = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Proyecto")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                items(projects) { project ->
                    ProjectCard(
                        project = project,
                        onComplete = { projectToComplete ->
                            project.isCompleted = true
                        },
                        onDelete = { projectToDelete ->
                            projects.remove(projectToDelete)
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        if (showDialog.value) {
            NewProjectDialog(
                projects = projects,
                onDismiss = { showDialog.value = false },
                onAddProject = { newProject ->
                    projects.add(newProject)
                    showDialog.value = false
                }
            )
        }
    }
}

@Preview
@Composable
fun preview(){
    ProjectScreen()
}




