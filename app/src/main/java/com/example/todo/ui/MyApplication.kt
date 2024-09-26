package com.example.todo.ui

import android.app.Application
import com.example.todo.database.AppDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }

}
