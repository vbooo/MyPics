package com.pickupservices.mypics.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pickupservices.mypics.data.db.dao.AlbumDao
import com.pickupservices.mypics.data.db.dao.UserDao
import com.pickupservices.mypics.data.db.entity.AlbumEntity
import com.pickupservices.mypics.data.db.entity.UserEntity

/**
 * The [Room] database shared by many components
 */
@Database(
    entities = [
        AlbumEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MyPicsDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "my_pics_db"

        fun buildDatabase(context: Context): MyPicsDatabase {

            return Room.databaseBuilder(context, MyPicsDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
