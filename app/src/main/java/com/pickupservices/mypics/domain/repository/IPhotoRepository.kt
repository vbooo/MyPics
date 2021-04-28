package com.pickupservices.mypics.domain.repository

import com.pickupservices.mypics.domain.Result
import com.pickupservices.mypics.domain.model.Photo

interface IPhotoRepository {
    suspend fun getPhotosByAlbum(id: Int): Result<List<Photo>?>
    suspend fun refreshData()
}