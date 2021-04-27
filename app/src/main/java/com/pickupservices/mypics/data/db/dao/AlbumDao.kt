package com.pickupservices.mypics.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pickupservices.mypics.data.db.entity.AlbumEntity

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(album: AlbumEntity)

    @Query("SELECT * FROM album ORDER BY title")
    fun getAll(): List<AlbumEntity>

}