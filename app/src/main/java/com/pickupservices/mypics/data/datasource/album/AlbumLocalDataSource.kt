package com.pickupservices.mypics.data.datasource.album

import com.pickupservices.mypics.data.db.MyPicsDatabase
import com.pickupservices.mypics.data.db.entity.AlbumEntity
import com.pickupservices.mypics.data.network.response.GetAllAlbumsResponse
import javax.inject.Inject

class AlbumLocalDataSource @Inject constructor(
    private val appDatabase: MyPicsDatabase
)
{
    fun saveAlbums(listAlbum: List<GetAllAlbumsResponse>) {
        for (album in listAlbum) {
            appDatabase.albumDao().insert(
                album = AlbumEntity(
                album.id,
                album.title,
                album.userId
                )
            )
        }
    }

    fun getAll(): List<AlbumEntity> {
        return appDatabase.albumDao().getAll()
    }
}