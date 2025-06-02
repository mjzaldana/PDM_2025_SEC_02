package com.example.pagging3test.ui.Post

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

@Composable
fun PostListScreen(viewModel: PostViewModel = viewModel()){
    val posts = viewModel.posts.collectAsLazyPagingItems()
    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = {Text("Titulo")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        OutlinedTextField(
            value = body,
            onValueChange = { body = it },
            label = {Text("Contenido")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(
            onClick = {
                if(title.isNotBlank() && body.isNotBlank()){
                    viewModel.createPost(title, body){
                        Toast.makeText(context,
                            "Post realizado correctamente",
                            Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context,
                        "Llene los campos",
                        Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(16.dp).align(Alignment.End)
        ) {
            Text("Crear Post")
        }

        LazyColumn {
            items(posts.itemCount){ index ->
                val post = posts[index]

                if(post != null){
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    ) {
                        Text(text = post.title ?: "Sin titulo")
                        Text(text = post.body ?: "Sin Contenido")
                    }
                }
            }
        }
    }
}

