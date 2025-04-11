package com.example.test_mvvm_app.data.repository

import com.example.test_mvvm_app.data.local.dao.HabitDao
import com.example.test_mvvm_app.model.Habit
import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao){
    suspend fun insert(habit: Habit) {
        habitDao.insert(habit)
    }

    suspend fun update(habit: Habit){
        habitDao.update(habit)
    }

    suspend fun delete(habit: Habit){
        habitDao.delete(habit)
    }

    var allHabits: Flow<List<Habit>> = habitDao.getAllHabits()

    suspend fun getHabitById(id: Int): Habit? {
        return habitDao.getHabitsById(id)
    }
}