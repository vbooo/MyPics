package com.pickupservices.mypics.data.datasource.photo

import com.pickupservices.mypics.data.db.MyPicsDatabase
import com.pickupservices.mypics.data.db.entity.PhotoEntity
import com.pickupservices.mypics.data.network.response.GetAllPhotosResponse
import com.pickupservices.mypics.domain.model.Photo
import javax.inject.Inject

class PhotoLocalDataSource @Inject constructor(
    private val appDatabase: MyPicsDatabase
)
{
    fun savePhotos(listPhoto: List<GetAllPhotosResponse>) {
        for (photo in listPhoto) {
            appDatabase.photoDao().insert(
                photo = PhotoEntity(
                    photo.id,
                    photo.albumId,
                    photo.thumbnailUrl
                )
            )
        }
    }
    fun getPhotosByAlbum(idAlbum: Int): List<Photo> {
        val response = appDatabase.photoDao().getPhotosByAlbum(idAlbum)
        val listPhoto = mutableListOf<Photo>()
        for (photo in response) {
            listPhoto.add(
                Photo(
                    photo.id,
                    photo.idAlbum,
                    photo.thumbnailUrl
                )
            )
        }
        return listPhoto
    }
}