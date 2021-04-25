package com.pickupservices.mypics.data.network.service

import com.pickupservices.mypics.data.network.response.GetAllAlbumsResponse
import retrofit2.http.GET

interface AlbumService {

    @GET("albums")
    suspend fun getAll(): List<GetAllAlbumsResponse>
}