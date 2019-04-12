package com.app.task.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "search_request")
data class SearchRequest(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "start_latitude")
    val startLatitude: Double,

    @ColumnInfo(name = "start_longitude")
    val startLongitude: Double,

    @ColumnInfo(name = "end_latitude")
    val endLatitude: Double,

    @ColumnInfo(name = "end_longitude")
    val endLongitude: Double,

    @ColumnInfo(name = "distance")
    val distance: Double
)