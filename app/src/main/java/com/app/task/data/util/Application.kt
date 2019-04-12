package com.app.task.data.util

import android.arch.persistence.room.Room
import android.support.multidex.MultiDexApplication

class Application : MultiDexApplication() {
    lateinit var db: AppDataBase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this,
            AppDataBase::class.java, "task.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}