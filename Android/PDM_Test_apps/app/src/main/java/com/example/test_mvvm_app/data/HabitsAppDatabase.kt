package com.example.test_mvvm_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test_mvvm_app.data.local.dao.HabitDao
import com.example.test_mvvm_app.model.Habit

@Database(entities = [Habit::class], version = 1)
abstract class HabitsAppDatabase : RoomDatabase(){

    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: HabitsAppDatabase? = null

        fun getDatabase(context: Context): HabitsAppDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitsAppDatabase::class.java,
                    "habit_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

