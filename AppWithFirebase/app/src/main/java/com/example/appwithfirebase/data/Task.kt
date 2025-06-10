package com.example.appwithfirebase.data

import com.google.firebase.firestore.DocumentId

data class Task(
    @DocumentId
    val id: String = "",
    val title: String = "",
    val completed: Boolean = false
)