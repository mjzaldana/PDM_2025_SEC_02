package com.example.pagging3test.ui.Post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagging3test.model.NewPost
import com.example.pagging3test.model.Post
import com.example.pagging3test.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel: ViewModel(){
    private val repository = PostRepository()
    //GET POSTS
    val posts: Flow<PagingData<Post>> = repository.getPosts().cachedIn(viewModelScope)

    //CREATE POST
    fun createPost(title: String, body: String, onSuccess: () -> Unit = {}){
        viewModelScope.launch {
            val newPost = NewPost(userId = 1, title, body)
            repository.createPost(newPost)
            onSuccess()
        }
    }
}
