package com.example.test_mvvm_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val category: String,
    val dailyGoal: Int,
    val totalProgress: Int = 0,
    val creationDate: Long = System.currentTimeMillis()
)


