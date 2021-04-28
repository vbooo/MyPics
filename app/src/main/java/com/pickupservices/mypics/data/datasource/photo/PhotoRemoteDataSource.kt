package com.pickupservices.mypics.data.datasource.photo

import com.pickupservices.mypics.data.network.response.GetAllPhotosResponse
import com.pickupservices.mypics.data.network.service.PhotoService
import javax.inject.Inject

class PhotoRemoteDataSource  @Inject constructor(private val photoService: PhotoService) {

    suspend fun getAll(): List<GetAllPhotosResponse> {
        return photoService.getAll()
    }
}