package com.example.todo.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.database.dao.TaskDao
import com.example.todo.database.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tasksDao(): TaskDao

    companion object {
        private var db: AppDatabase? = null
        private const val datebaseName = "Tasks-database"
        fun getInstance(): AppDatabase {
            return db!!
        }

        fun init(applicationContext: Application) {
            if (db == null) {
                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, datebaseName
                ).allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}