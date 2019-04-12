package com.app.task.data.util

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.app.task.data.dao.SearchRequestDao
import com.app.task.data.model.SearchRequest

@Database(entities = [SearchRequest::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun searchRequestDAO(): SearchRequestDao
}