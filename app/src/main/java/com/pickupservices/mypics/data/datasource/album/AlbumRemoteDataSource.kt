package com.pickupservices.mypics.data.datasource.album

import com.pickupservices.mypics.data.network.response.GetAllAlbumsResponse
import com.pickupservices.mypics.data.network.service.AlbumService
import javax.inject.Inject

class AlbumRemoteDataSource  @Inject constructor(private val albumService: AlbumService) {

    suspend fun getAll(): List<GetAllAlbumsResponse> {
        return albumService.getAll()
    }
}