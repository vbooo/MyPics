package com.pickupservices.mypics.domain.repository

import com.pickupservices.mypics.domain.Result
import com.pickupservices.mypics.domain.model.Album

interface IAlbumRepository {
    suspend fun getAllAlbums(): Result<List<Album>?>
    suspend fun refreshData()
}