package com.pickupservices.mypics.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class AlbumEntity (
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "idUser") val idUser: Int
)
