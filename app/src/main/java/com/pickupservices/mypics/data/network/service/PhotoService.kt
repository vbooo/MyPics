package com.pickupservices.mypics.data.network.service

import com.pickupservices.mypics.data.network.response.GetAllPhotosResponse
import retrofit2.http.GET

interface PhotoService {

    @GET("photos")
    suspend fun getAll(): List<GetAllPhotosResponse>
}