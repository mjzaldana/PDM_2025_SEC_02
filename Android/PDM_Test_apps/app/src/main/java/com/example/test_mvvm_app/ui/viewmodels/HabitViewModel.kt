package com.example.test_mvvm_app.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData
import com.example.test_mvvm_app.data.HabitsAppDatabase
import com.example.test_mvvm_app.data.repository.HabitRepository
import com.example.test_mvvm_app.model.Habit

class HabitViewModel(application: Application): AndroidViewModel(application){
    private val repository: HabitRepository
    val allHabits: LiveData<List<Habit>>

    init {
        val habitDao = HabitsAppDatabase.getDatabase(application).habitDao()
        repository = HabitRepository(habitDao)
        allHabits = repository.allHabits.asLiveData()
    }

    fun insert(habit: Habit) = viewModelScope.launch {
        repository.insert(habit)
    }

    fun update(habit: Habit) = viewModelScope.launch {
        repository.update(habit)
    }

    fun delete(habit: Habit) = viewModelScope.launch {
        repository.delete(habit)
    }

    fun incrementProgress(habit: Habit) = viewModelScope.launch {
        val updatedHabit = habit.copy(totalProgress = habit.totalProgress + 1)
        repository.update(updatedHabit)
    }
}