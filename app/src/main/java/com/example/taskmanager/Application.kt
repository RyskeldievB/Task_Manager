package com.example.taskmanager

import android.app.Application
import androidx.room.Room
import com.example.taskmanager.local.db.AppDataBase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        db= Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "task-name"
        ).allowMainThreadQueries().build()
    }
    companion object{
        lateinit var db: AppDataBase
    }
}