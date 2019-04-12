package com.app.task.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.app.task.data.model.SearchRequest

@Dao
interface SearchRequestDao{
    @Query("SELECT * from search_request")
    fun getAll(): List<SearchRequest>

    @Query( "SELECT * FROM search_request ORDER BY id DESC LIMIT 10")
    fun getLastTenQueries(): List<SearchRequest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchRequest: SearchRequest)
}