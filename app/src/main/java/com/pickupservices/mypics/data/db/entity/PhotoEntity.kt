package com.pickupservices.mypics.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class PhotoEntity (
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "idAlbum") val idAlbum: Int,
    @ColumnInfo(name = "thumbnailUrl") val thumbnailUrl: String
)
