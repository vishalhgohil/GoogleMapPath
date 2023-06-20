package com.app.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "location_room",
    indices = [Index(value = arrayOf("location_id"), unique = true)]
)
data class LocationDetailsRoom(
    @PrimaryKey(autoGenerate = true) var location_id: Int = 0,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "latitude") var latitude: String? = null,
    @ColumnInfo(name = "longitute") var longitute: String? = null,
)




