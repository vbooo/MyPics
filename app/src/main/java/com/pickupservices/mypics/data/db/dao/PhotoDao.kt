package com.pickupservices.mypics.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pickupservices.mypics.data.db.entity.PhotoEntity

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: PhotoEntity)

    @Query("SELECT * FROM photo WHERE idAlbum = :id")
    fun getPhotosByAlbum(id: Int): List<PhotoEntity>

}