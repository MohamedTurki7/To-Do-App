//package com.example.todo.database
//
//import android.app.Application
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.todo.database.dao.TaskDao
//import com.example.todo.database.model.Task
//import com.example.todo.ui.MyApplication
//
//@Database(entities = [Task::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun tasksDao(): TaskDao
//
//    companion object {
//        private var db: AppDatabase? = null
//        private const val datebaseName = "Tasks-database"
//        fun getInstance(): AppDatabase {
//            return db!!
//        }
//
//        fun init(applicationContext: Application) {
//            if (db == null) {
//                val db = Room.databaseBuilder(
//                    applicationContext,
//                    AppDatabase::class.java, datebaseName
//                ).allowMainThreadQueries().fallbackToDestructiveMigration()
//                    .build()
//            }
//        }
//    }
//}
package com.example.todo.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.database.dao.TaskDao
import com.example.todo.database.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tasksDao(): TaskDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null
        private const val databaseName = "Tasks-database"

        fun getInstance(): AppDatabase {
            return db
                ?: throw IllegalStateException("Database is not initialized. Call init() first.")
        }

        //            return db!!
        fun init(applicationContext: Application) {
            if (db == null) {
                synchronized(this) {
                    if (db == null) {
                        db = Room.databaseBuilder(
                            applicationContext,
                            AppDatabase::class.java, databaseName
                        ).allowMainThreadQueries() // Consider moving this to background thread
                            .fallbackToDestructiveMigration()
                            .build()

                    }
                }
            }
        }
    }
}
