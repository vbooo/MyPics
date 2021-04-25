package com.pickupservices.mypics.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pickupservices.mypics.data.db.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Query("SELECT * FROM user WHERE id = :idUser")
    fun getById(idUser: Int): UserEntity

}