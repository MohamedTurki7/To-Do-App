package com.example.todo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.database.model.Task

@Dao

interface TaskDao {
    @Insert
    fun createTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("select * from Task")
    fun getAllTasks(): List<Task>

    @Query("select * from Task where date=:date")
    fun getTaskByDate(date: Long): List<Task>


}