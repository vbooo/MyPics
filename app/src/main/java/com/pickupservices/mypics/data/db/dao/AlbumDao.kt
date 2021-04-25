package com.pickupservices.mypics.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.pickupservices.mypics.data.db.entity.AlbumEntity

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(album: AlbumEntity)

}